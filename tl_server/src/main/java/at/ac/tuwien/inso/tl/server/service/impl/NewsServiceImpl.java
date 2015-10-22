package at.ac.tuwien.inso.tl.server.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.NewsDao;
import at.ac.tuwien.inso.tl.model.News;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService
{

	private static final Logger LOG = Logger.getLogger(NewsServiceImpl.class);

	@Autowired
	private NewsDao newsDao;

	@Override
	public News getNews(Integer id) throws ServiceException
	{
		try
		{
			return newsDao.findOne(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public News save(News news) throws ServiceException
	{
		try
		{
			news.setCreatedOn(new Date());
			return newsDao.save(news);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> getAllNews() throws ServiceException
	{
		try
		{
			return newsDao.findAllOrderByCreatedOnDesc();
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> getNews(String isoDate) throws ServiceException, ParseException
	{
		Date date = new SimpleDateFormat("yyyy-MM-dd'T'HHmm'Z'").parse(isoDate);
		LOG.info("NewsServiceImpl String to Date: " + date);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		LOG.info("NewsServiceImpl util.Date to sql.Date: " + sqlDate);
		return newsDao.findByDateOrderByCreatedOnDesc(sqlDate);
	}

	// -------------------- For Testing purposes --------------------

	public void setNewsDao(NewsDao dao)
	{
		this.newsDao = dao;
	}

	// -------------------- For Testing purposes --------------------
}
