package at.ac.tuwien.inso.tl.client.client.rest;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.inso.tl.client.client.TicketService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.ShowDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

@Component
public class TicketRestClient implements TicketService
{

	private static final Logger LOG = Logger.getLogger(TicketRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public List<TicketDto> getAllTickets() throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/tickets/");

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving tickets from " + url);

		List<TicketDto> tickets = null;

		try
		{
			ParameterizedTypeReference<List<TicketDto>> ref = new ParameterizedTypeReference<List<TicketDto>>()
			{
			};
			ResponseEntity<List<TicketDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			tickets = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve tickets: " + e.getMessage(), e);
		}
		LOG.info("Received " + tickets.size() + " tickets");

		return tickets;
	}
}