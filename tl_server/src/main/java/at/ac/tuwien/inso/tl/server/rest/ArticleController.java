package at.ac.tuwien.inso.tl.server.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.server.exception.ArtistException;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ArticleService;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController
{
	private static final Logger LOG = Logger.getLogger(ArticleController.class);

	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ArticleDto findArticleById(@PathVariable("id") Integer id) throws ServiceException
	{
		LOG.info("findArticleById() called");

		return EntityToDto.convert(this.articleService.findArticleById(id));
	}
	
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ArticleDto> findArticles(@RequestParam(value = "search", required = false) String search) throws ArtistException, ServiceException
	{
		LOG.info("findArticle called. search for: " + search);
		
		return EntityToDto.convertArticles(this.articleService.findArticles(search));
	}
	
}
