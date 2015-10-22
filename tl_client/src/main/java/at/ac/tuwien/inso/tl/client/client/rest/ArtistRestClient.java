package at.ac.tuwien.inso.tl.client.client.rest;

import java.net.URI;
import java.util.ArrayList;
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

import at.ac.tuwien.inso.tl.client.client.ArtistService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.LocationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;

@Component
public class ArtistRestClient implements ArtistService
{

	private static final Logger LOG = Logger.getLogger(ArtistRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public List<ArtistDto> findArtists(String search) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String queryString = search.isEmpty() ? "" : "?search=" + search;
		String url = this.restClient.createServiceUrl("/artists" + queryString);

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving artists from " + url);

		List<ArtistDto> artists = null;
		try
		{
			ParameterizedTypeReference<List<ArtistDto>> ref = new ParameterizedTypeReference<List<ArtistDto>>()
			{
			};
			ResponseEntity<List<ArtistDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			artists = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve artists: " + e.getMessage(), e);
		}

		LOG.info("Received " + artists.size() + " artists");

		return artists;
	}

	@Override
	public ArtistDto getArtistById(ArtistDto artist) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/artists/" + artist.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving artists from " + url);
		ArtistDto result = null;
		try
		{
			ParameterizedTypeReference<ArtistDto> ref = new ParameterizedTypeReference<ArtistDto>()
			{
			};
			ResponseEntity<ArtistDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve artist: " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	public List<PerformanceDto> getPerformancesByArtistId(ArtistDto artist) throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/artists/" + artist.getId()
				+ "/performances");

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving performances for artist from " + url);

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
