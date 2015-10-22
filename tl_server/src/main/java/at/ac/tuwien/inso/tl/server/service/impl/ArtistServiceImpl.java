package at.ac.tuwien.inso.tl.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.ArtistDao;
import at.ac.tuwien.inso.tl.model.Artist;
import at.ac.tuwien.inso.tl.server.exception.ArtistException;
import at.ac.tuwien.inso.tl.server.service.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService
{

	@Autowired
	private ArtistDao artistDao;

	@Override
	public Artist getArtist(Integer id) throws ArtistException {
		if(id == null)
			throw new ArtistException();
		
		try {
			return artistDao.findByIdWithParticipations(id);
		} catch (Exception e)
		{
			throw new ArtistException(e);
		}
	}

	@Override
	public List<Artist> findArtists(String search) throws ArtistException {
		try {
			if (search == null || search.isEmpty())
			{
				return artistDao.findAll();
			} else
			{
				return artistDao.findArtistsByFirstnameLastname(search.toUpperCase());
			}
		} catch (Exception e)
		{
			throw new ArtistException(e);
		}
	}

	public void setArtistDao(ArtistDao dao)
	{
		this.artistDao = dao;
	}
	
}
