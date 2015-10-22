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

import at.ac.tuwien.inso.tl.client.client.PerformanceService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;

@Component
public class PerformanceRestClient implements PerformanceService
{
	private static final Logger LOG = Logger.getLogger(PerformanceRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public List<PerformanceDto> findPerformances(String search) throws ServiceException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerformanceDto getPerformanceById(PerformanceDto performance) throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/performances/" + performance.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving performance from " + url);

		PerformanceDto result = null;
		try
		{
			ParameterizedTypeReference<PerformanceDto> ref = new ParameterizedTypeReference<PerformanceDto>()
			{
			};
			ResponseEntity<PerformanceDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve performance: " + e.getMessage(), e);
		}

		return result;
	}

}
