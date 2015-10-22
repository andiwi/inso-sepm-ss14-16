package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.model.Artist;
import at.ac.tuwien.inso.tl.server.exception.ArtistException;

public interface ArtistService
{
	/**
	 * Returns the artist object identified by the given id
	 * 
	 * @param id
	 * @return Artist
	 * @throws ServiceException
	 */
	public Artist getArtist(Integer id) throws ArtistException;

	/**
	 * Returns a collection of all artists whose firstname or lastname contains
	 * the given search string.
	 * 
	 * @param search string
	 * @return List<Artist>
	 * @throws ServiceException
	 */
	public List<Artist> findArtists(String search) throws ArtistException;
}