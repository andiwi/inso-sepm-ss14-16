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

import at.ac.tuwien.inso.tl.client.client.ReservationService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.exception.ValidationException;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.MessageDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

@Component
public class ReservationRestClient implements ReservationService
{
	private static final Logger LOG = Logger.getLogger(ReservationRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public ReservationDto reserveTickets(List<TicketDto> tickets, CustomerDto customer)
			throws ServiceException
	{
		ReservationDto reservation = new ReservationDto();
		List<TicketDto> ts = new ArrayList<TicketDto>();
		for (TicketDto t : tickets)
		{
			TicketDto tmp = new TicketDto();
			tmp.setId(t.getId());
			ts.add(tmp);
		}
		CustomerDto c = new CustomerDto();
		c.setId(customer.getId());
		reservation.setCustomer(c);
		reservation.setTickets(ts);

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/reservations");

		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<ReservationDto> entity = new HttpEntity<ReservationDto>(reservation, headers);

		LOG.info("Posting reservation to " + url);

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
			throw new ServiceException("Could not create reservation: " + e.getMessage(), e);
		}

		Integer id = null;
		try
		{
			id = Integer.valueOf(msg.getText());
		} catch (NumberFormatException e)
		{
			throw new ServiceException("Invalid ID: " + msg.getText());
		}

		reservation.setId(id);
		// TODO reservation.setReservationNumber(reservationNumber);
		return reservation;
	}

	@Override
	public ReservationDto getReservationById(ReservationDto reservation) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/reservations/" + reservation.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving reservation from " + url);

		ReservationDto result = null;
		try
		{
			ParameterizedTypeReference<ReservationDto> ref = new ParameterizedTypeReference<ReservationDto>()
			{
			};
			ResponseEntity<ReservationDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve reservation: " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	public List<ReservationDto> findReservations(String search) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String queryString = search.isEmpty() ? "" : "?search=" + search;
		String url = this.restClient.createServiceUrl("/reservations" + queryString);

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving reservations from " + url);

		List<ReservationDto> reservations = null;
		try
		{
			ParameterizedTypeReference<List<ReservationDto>> ref = new ParameterizedTypeReference<List<ReservationDto>>()
			{
			};
			ResponseEntity<List<ReservationDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			reservations = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve reservations: " + e.getMessage(), e);
		}

		LOG.info("Received " + reservations.size() + " reservations");

		return reservations;
	}

	@Override
	public ReservationDto update(ReservationDto reservation, List<TicketDto> tickets,
			CustomerDto customer) throws ServiceException
	{

		ReservationDto r = new ReservationDto();
		r.setId(reservation.getId());
		List<TicketDto> ts = new ArrayList<TicketDto>();
		for (TicketDto t : tickets)
		{
			TicketDto tmp = new TicketDto();
			tmp.setId(t.getId());
			ts.add(tmp);
		}
		r.setCustomer(customer);
		r.setTickets(ts);

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/reservations/" + r.getId());

		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<ReservationDto> entity = new HttpEntity<ReservationDto>(r, headers);

		LOG.info("Putting reservation to " + url);

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
			throw new ServiceException("Could not update reservation: " + e.getMessage(), e);
		}

		// TODO: funktioniert das error handling?

		return r;
	}

	@Override
	public void cancel(ReservationDto reservation) throws ServiceException
	{

		ReservationDto r = new ReservationDto();
		r.setId(reservation.getId());

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/reservations/" + r.getId());

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
			throw new ServiceException("Could not cancel reservation: " + e.getMessage(), e);
		}

		// TODO: funktioniert das error handling?
	}

}
