package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;

public interface ArtistService
{

	public List<ArtistDto> findArtists(String search) throws ServiceException;

	public ArtistDto getArtistById(ArtistDto artist) throws ServiceException;

	public List<PerformanceDto> getPerformancesByArtistId(ArtistDto artist) throws ServiceException;

}
