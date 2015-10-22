package at.ac.tuwien.inso.tl.client.client.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import at.ac.tuwien.inso.tl.client.client.CustomerService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.exception.ValidationException;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.MessageDto;

@Component
public class CustomerRestClient implements CustomerService
{

	private static final Logger LOG = Logger.getLogger(CustomerRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public List<CustomerDto> findCustomers(String search) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String queryString = "";
		try
		{
			queryString = search.isEmpty() ? "" : "?search=" + URLEncoder.encode(search, "UTF-8");
		} catch (UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = this.restClient.createServiceUrl("/customers" + queryString);

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving customers from " + url);

		List<CustomerDto> customers = null;
		try
		{
			ParameterizedTypeReference<List<CustomerDto>> ref = new ParameterizedTypeReference<List<CustomerDto>>()
			{
			};
			ResponseEntity<List<CustomerDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			customers = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve customers: " + e.getMessage(), e);
		}

		LOG.info("Received " + customers.size() + " customers");

		return customers;
	}

	@Override
	public CustomerDto getCustomerById(CustomerDto customer) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/customers/" + customer.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving customer from " + url);

		CustomerDto result = null;
		try
		{
			ParameterizedTypeReference<CustomerDto> ref = new ParameterizedTypeReference<CustomerDto>()
			{
			};
			ResponseEntity<CustomerDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve customer: " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customer) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/customers");

		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<CustomerDto> entity = new HttpEntity<CustomerDto>(customer, headers);

		LOG.info("Posting customer to " + url);

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
			throw new ServiceException("Could not publish news: " + e.getMessage(), e);
		}

		Integer id = null;
		try
		{
			id = Integer.valueOf(msg.getText());
		} catch (NumberFormatException e)
		{
			throw new ServiceException("Invalid ID: " + msg.getText());
		}

		CustomerDto c = new CustomerDto();
		c.setId(id);
		return c;
	}

	@Override
	public CustomerDto updateCustomer(CustomerDto customer) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/customers/" + customer.getId());

		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<CustomerDto> entity = new HttpEntity<CustomerDto>(customer, headers);

		LOG.info("Putting customer to " + url);

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
			throw new ServiceException("Could not publish news: " + e.getMessage(), e);
		}

		return customer;
	}

}
