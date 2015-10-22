package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.model.Article;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface ArticleService
{

	/**
	 * Returns the article with the given id.
	 * Returns null if no such article exists.
	 * 
	 * @param id
	 * @return Article or null
	 * @throws ServiceException
	 */
	public Article findArticleById(Integer id) throws ServiceException;
	
	
	/**
	 * Returns a list of articles whose description or title matches the given string.
	 * 
	 * @param search
	 * @return List<Article>
	 * @throws ServiceException
	 */
	public List<Article> findArticles(String search) throws ServiceException;

}
