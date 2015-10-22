package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.model.Location;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface LocationService
{
	/**
	 * Returns the location object identified by the given id
	 * 
	 * @param id
	 *            of the location object
	 * @return the location object
	 * @throws ServiceException
	 */
	public Location getLocation(Integer id) throws ServiceException;

	/**
	 * Returns a collection of all locations matching the given search string.
	 * 
	 * @param search
	 *            string to look for
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Location> findLocations(String search) throws ServiceException;
}
