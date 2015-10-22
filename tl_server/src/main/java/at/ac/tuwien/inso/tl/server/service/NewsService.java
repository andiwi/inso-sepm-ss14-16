package at.ac.tuwien.inso.tl.server.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import at.ac.tuwien.inso.tl.model.News;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface NewsService
{

	/**
	 * Returns the news object identified by the given id
	 * 
	 * @param id
	 *            of the news object
	 * @return the news object
	 * @throws ServiceException
	 */
	public News getNews(Integer id) throws ServiceException;

	/**
	 * Saves the given news object and returns the saved entity
	 * 
	 * @param news
	 *            object to persist
	 * @return the saved entity
	 * @throws ServiceException
	 */
	public News save(News news) throws ServiceException;

	/**
	 * Returns a collection of all news.
	 * 
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<News> getAllNews() throws ServiceException;

	/**
	 * Returns a collection of all news that are newer than the given date.
	 * 
	 * @return java.util.List
	 * @throws ServiceException
	 * @throws ParseException
	 */
	public List<News> getNews(String date) throws ServiceException, ParseException;

}
