package at.ac.tuwien.inso.tl.client.client.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.inso.tl.client.client.LocationService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.LocationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;

@Component
public class LocationRestClient implements LocationService
{

	private static final Logger LOG = Logger.getLogger(LocationRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public List<LocationDto> findLocations(String search) throws ServiceException
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
		String url = this.restClient.createServiceUrl("/locations" + queryString);

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving locations from " + url);

		List<LocationDto> locations = null;
		try
		{
			ParameterizedTypeReference<List<LocationDto>> ref = new ParameterizedTypeReference<List<LocationDto>>()
			{
			};
			ResponseEntity<List<LocationDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			locations = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve locations: " + e.getMessage(), e);
		}

		LOG.info("Received " + locations.size() + " locations");

		return locations;
	}

	@Override
	public LocationDto getLocationById(LocationDto location) throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/locations/" + location.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving location from " + url);

		LocationDto result = null;
		try
		{
			ParameterizedTypeReference<LocationDto> ref = new ParameterizedTypeReference<LocationDto>()
			{
			};
			ResponseEntity<LocationDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve location: " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	public List<PerformanceDto> getPerformancesByLocationId(LocationDto location)
			throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/locations/" + location.getId()
				+ "/performances");

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving performances for location from " + url);

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
