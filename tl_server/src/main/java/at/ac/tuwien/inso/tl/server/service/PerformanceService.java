package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface PerformanceService
{

	/**
	 * Returns a collection of all performances that take place in a room in the
	 * location with the given id
	 * 
	 * @param the
	 *            location's id
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Performance> getPerformancesByLocationId(Integer id) throws ServiceException;

	/**
	 * Returns a collection of all performances where the Artist with the given
	 * ID participates
	 * 
	 * @param the
	 *            artist's id
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Performance> getPerformancesByArtistId(Integer id) throws ServiceException;

	/**
	 * Returns the performance with the given id
	 * 
	 * @param the
	 *            location's id
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public Performance getPerformance(Integer id) throws ServiceException;

	/**
	 * Returns a collection of all performances of the show with the given id
	 * 
	 * @param the
	 *            show's id
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Performance> getPerformancesByShowId(Integer id) throws ServiceException;
}