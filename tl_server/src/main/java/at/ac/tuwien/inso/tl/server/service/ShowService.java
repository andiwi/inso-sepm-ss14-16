package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.model.Show;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface ShowService
{
	/**
	 * Returns the show object identified by the given id
	 * 
	 * @param id
	 *            of the show object
	 * @return the show object
	 * @throws ServiceException
	 */
	public Show getShow(Integer id) throws ServiceException;

	/**
	 * Returns a collection of all shows whose title, showType, description
	 * contains the given search string.
	 * 
	 * @param search
	 *            string to look for
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Show> findShows(String search) throws ServiceException;

	/**
	 * Returns a collection of all Shows that are performed by the Artist with
	 * the given id
	 * 
	 * @param the
	 *            artist's id
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Show> getShowsByArtistId(Integer id) throws ServiceException;
}
