package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;

public interface ShowService
{

	public List<ShowDto> findShows(String search) throws ServiceException;

	public ShowDto getShowById(ShowDto show) throws ServiceException;

	public List<PerformanceDto> getPerformancesByShowId(ShowDto show) throws ServiceException;

}
