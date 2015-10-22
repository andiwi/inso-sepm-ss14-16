package at.ac.tuwien.inso.tl.client.client.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
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

import at.ac.tuwien.inso.tl.client.client.ShowService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.LocationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;

@Component
public class ShowRestClient implements ShowService
{

	private static final Logger LOG = Logger.getLogger(ShowRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public List<ShowDto> findShows(String search) throws ServiceException
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
		String url = this.restClient.createServiceUrl("/shows" + queryString);

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving shows from " + url);

		List<ShowDto> shows = null;
		try
		{
			ParameterizedTypeReference<List<ShowDto>> ref = new ParameterizedTypeReference<List<ShowDto>>()
			{
			};
			ResponseEntity<List<ShowDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			shows = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve shows: " + e.getMessage(), e);
		}

		LOG.info("Received " + shows.size() + " shows");

		return shows;

		/*
		 * 
		 * 
		 * 
		 * ShowDto s = new ShowDto(); s.setId(1);
		 * s.setDescription("description"); s.setTitle("ti");
		 * 
		 * ArrayList<ShowDto> l = new ArrayList<ShowDto>(); l.add(s);
		 * 
		 * return l;
		 */
	}

	@Override
	public ShowDto getShowById(ShowDto show) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/shows/" + show.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving show from " + url);

		ShowDto result = null;
		try
		{
			ParameterizedTypeReference<ShowDto> ref = new ParameterizedTypeReference<ShowDto>()
			{
			};
			ResponseEntity<ShowDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve show: " + e.getMessage(), e);
		}

		return result;

		/*
		 * 
		 * s.setTitle("ti"); s.setDescription("de");
		 * 
		 * PerformanceDto p = new PerformanceDto(); //p.setStartsAt("st");
		 * ArrayList<PerformanceDto> l = new ArrayList<PerformanceDto>();
		 * l.add(p); s.setPerformances(l);
		 * 
		 * return s;
		 */
	}

	@Override
	public List<PerformanceDto> getPerformancesByShowId(ShowDto show) throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/shows/" + show.getId() + "/performances");

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving performances for show from " + url);

		List<PerformanceDto> performances = null;
		try
		{
			ParameterizedTypeReference<List<PerformanceDto>> ref = new ParameterizedTypeReference<List<PerformanceDto>>()
			{
			};
			ResponseEntity<List<PerformanceDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			performances = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve performances: " + e.getMessage(), e);
		}

		LOG.info("Received " + performances.size() + " performances");

		return performances;
	}

}
