package at.ac.tuwien.inso.tl.server.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.ArtistDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.server.exception.ArtistException;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ArtistService;
import at.ac.tuwien.inso.tl.server.service.PerformanceService;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController
{

	private static final Logger LOG = Logger.getLogger(ArtistController.class);

	@Autowired
	private ArtistService artistService;

	@Autowired
	private PerformanceService performanceService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ArtistDto> findArtists(
			@RequestParam(value = "search", required = false) String search) throws ArtistException
	{
		LOG.info("findArtists called. search for: " + search);

		return EntityToDto.convertArtists(this.artistService.findArtists(search));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ArtistDto getArtistById(@PathVariable("id") Integer id) throws ServiceException,
			ArtistException
	{
		LOG.info("getArtist() called");
		return EntityToDto.convert(artistService.getArtist(id));
	}

	@RequestMapping(value = "/{id}/performances", method = RequestMethod.GET, produces = "application/json")
	public List<PerformanceDto> getShowsByArtistId(@PathVariable("id") Integer id)
			throws ServiceException
	{
		LOG.info("getShowsByArtistId called. search for: " + id);
		return EntityToDto.convertPerformances(this.performanceService
				.getPerformancesByArtistId(id));
	}
}
