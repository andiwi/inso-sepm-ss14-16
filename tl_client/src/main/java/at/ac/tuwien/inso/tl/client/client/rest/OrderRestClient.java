package at.ac.tuwien.inso.tl.client.client.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.inso.tl.client.client.OrderService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.exception.ValidationException;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.MessageDto;
import at.ac.tuwien.inso.tl.dto.MethodOfPayment;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.OrderItemDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

@Component
public class OrderRestClient implements OrderService
{
	private static final Logger LOG = Logger.getLogger(OrderRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public OrderDto getOrderById(OrderDto order) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/orders/" + order.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving order from " + url);

		OrderDto result = null;
		try
		{
			ParameterizedTypeReference<OrderDto> ref = new ParameterizedTypeReference<OrderDto>()
			{
			};
			ResponseEntity<OrderDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve order: " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	public List<OrderDto> findOrders(String search) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String queryString = search.isEmpty() ? "" : "?search=" + search;
		String url = this.restClient.createServiceUrl("/orders" + queryString);

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving orders from " + url);

		List<OrderDto> orders = null;
		try
		{
			ParameterizedTypeReference<List<OrderDto>> ref = new ParameterizedTypeReference<List<OrderDto>>()
			{
			};
			ResponseEntity<List<OrderDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			orders = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve orders: " + e.getMessage(), e);
		}

		LOG.info("Received " + orders.size() + " orders");

		return orders;
	}

	@Override
	public OrderDto update(
			OrderDto order,
			List<TicketDto> tickets,
			List<ArticleDto> articles,
			CustomerDto customer,
			MethodOfPayment methodOfPayment)
			throws ServiceException
	{

		OrderDto o = this.createOrderDto(tickets, articles, customer, methodOfPayment, null);
		o.setId(order.getId());

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/orders/" + o.getId());

		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<OrderDto> entity = new HttpEntity<OrderDto>(o, headers);

		try
		{
			restTemplate.put(url, entity);
		} catch (HttpStatusCodeException e)
		{
			MessageDto errorMsg = this.restClient.mapExceptionToMessage(e);

			if (errorMsg.hasFieldErrors())
			{
				throw new ValidationException(errorMsg.getFieldErrors());
			} else
			{
				throw new ServiceException(errorMsg.getText());
			}
		} catch (RestClientException e)
		{
			throw new ServiceException("Exception: " + e.getMessage(), e);
		}

		return o;
	}

	@Override
	public void cancel(OrderDto order) throws ServiceException
	{

		OrderDto o = new OrderDto();
		o.setId(order.getId());

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/orders/" + o.getId());

		try
		{
			restTemplate.delete(url);
		} catch (HttpStatusCodeException e)
		{
			MessageDto errorMsg = this.restClient.mapExceptionToMessage(e);

			if (errorMsg.hasFieldErrors())
			{
				throw new ValidationException(errorMsg.getFieldErrors());
			} else
			{
				throw new ServiceException(errorMsg.getText());
			}
		} catch (RestClientException e)
		{
			throw new ServiceException("Exception: " + e.getMessage(), e);
		}
	}

	@Override
	public OrderDto orderTicketsAndArticles(
			List<TicketDto> tickets,
			List<ArticleDto> articles,
			CustomerDto customer,
			MethodOfPayment methodOfPayment,
			Boolean useBonusPoints) throws ServiceException
	{
		LOG.info("order tickets and articles");
		OrderDto order = this.createOrderDto(
				tickets,
				articles,
				customer,
				methodOfPayment,
				useBonusPoints);
		return this.orderOrderDto(order);
	}
	
	@Override
	public OrderDto orderReservationAndArticles(
			List<TicketDto> tickets,
			List<ArticleDto> articles,
			ReservationDto reservation,
			CustomerDto customer,
			MethodOfPayment methodOfPayment,
			Boolean useBonusPoints) throws ServiceException
	{
		LOG.info("order reservation and articles");
		OrderDto order = this.createOrderDto(
			tickets,
			articles,
			customer,
			methodOfPayment,
			useBonusPoints);
		ReservationDto r = new ReservationDto();
		r.setId(reservation.getId());
		order.setReservation(r);
		return this.orderOrderDto(order);
	}
	
	private OrderDto createOrderDto(
			List<TicketDto> tickets,
			List<ArticleDto> articles,
			CustomerDto customer,
			MethodOfPayment methodOfPayment,
			Boolean useBonusPoints)
	{
		OrderDto order = new OrderDto();
		
		List<OrderItemDto> items = new ArrayList<OrderItemDto>();
		
		for (TicketDto ticket : tickets)
		{
			TicketDto ttemp = new TicketDto();
			ttemp.setId(ticket.getId());

			OrderItemDto item = new OrderItemDto();
			item.setTicket(ttemp);
			
			items.add(item);
		}
		
		for (ArticleDto article : articles)
		{
			ArticleDto ttemp = new ArticleDto();
			ttemp.setId(article.getId());

			OrderItemDto item = new OrderItemDto();
			item.setArticle(ttemp);
			item.setAmount(article.getAmount());
			items.add(item);
		}
		order.setOrderItems(items);
		order.setUseBonusPoints(useBonusPoints);
		order.setMethodOfPayment(methodOfPayment);
		
		CustomerDto c = new CustomerDto();
		c.setId(customer.getId());
		order.setCustomer(c);
		
		return order;
	}
	
	private OrderDto orderOrderDto(OrderDto order) throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/orders");

		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<OrderDto> entity = new HttpEntity<OrderDto>(order, headers);

		LOG.info("Posting order to " + url);

		MessageDto msg = null;
		try
		{
			msg = restTemplate.postForObject(url, entity, MessageDto.class);
		} catch (HttpStatusCodeException e)
		{
			MessageDto errorMsg = this.restClient.mapExceptionToMessage(e);

			if (errorMsg.hasFieldErrors())
			{
				throw new ValidationException(errorMsg.getFieldErrors());
			} else
			{
				throw new ServiceException(errorMsg.getText());
			}
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not create order: " + e.getMessage(), e);
		}

		Integer id = null;
		try
		{
			id = Integer.valueOf(msg.getText());
		} catch (NumberFormatException e)
		{
			throw new ServiceException("Invalid ID: " + msg.getText());
		}

		OrderDto o = new OrderDto();
		o.setId(id);
		return o;
	}

}
