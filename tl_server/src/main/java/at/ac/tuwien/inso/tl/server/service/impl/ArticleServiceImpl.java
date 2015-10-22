package at.ac.tuwien.inso.tl.server.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.ArticleDao;
import at.ac.tuwien.inso.tl.model.Article;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService
{

	private static final Logger LOG = Logger.getLogger(ArticleServiceImpl.class);

	@Autowired
	private ArticleDao articleDao;

	@Override
	public Article findArticleById(Integer id) throws ServiceException
	{
		LOG.info("findArticleById(id) mit ID: " + id + " aufgerufen");

		try
		{
			return articleDao.findArticleById(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Article> findArticles(String search) throws ServiceException {
		LOG.info("findArticle() aufgerufen");		
		try
		{
			if (search == null || search.isEmpty())
			{
				return articleDao.findArticlesByName("");
			} else
			{
				return articleDao.findArticlesByName(search.toUpperCase());
			}
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}
}
