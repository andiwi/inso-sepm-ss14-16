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

import at.ac.tuwien.inso.tl.client.client.ArticleService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.NewsDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;

@Component
public class ArticleRestClient implements ArticleService
{

	private static final Logger LOG = Logger.getLogger(ArticleRestClient.class);

	@Autowired
	private RestClient restClient;

	@Override
	public ArticleDto findArticleById(ArticleDto article) throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/articles/" + article.getId());

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving article from " + url);

		ArticleDto result = null;
		try
		{
			ParameterizedTypeReference<ArticleDto> ref = new ParameterizedTypeReference<ArticleDto>()
			{
			};
			ResponseEntity<ArticleDto> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			result = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve article: " + e.getMessage(), e);
		}

		return result;
	}

	@Override
	public List<ArticleDto> findArticles(String search) throws ServiceException
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
		String url = this.restClient.createServiceUrl("/articles" + queryString);

		HttpEntity<String> entity = new HttpEntity<String>(this.restClient.getHttpHeaders());

		LOG.info("Retrieving articles from " + url);

		List<ArticleDto> articles = null;
		try
		{
			ParameterizedTypeReference<List<ArticleDto>> ref = new ParameterizedTypeReference<List<ArticleDto>>()
			{
			};
			ResponseEntity<List<ArticleDto>> response = restTemplate.exchange(URI.create(url),
					HttpMethod.GET, entity, ref);
			articles = response.getBody();
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not retrieve articles: " + e.getMessage(), e);
		}

		LOG.info("Received " + articles.size() + " articles");

		return articles;
	}

}
