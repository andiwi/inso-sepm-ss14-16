package at.ac.tuwien.inso.tl.server.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.ShowDto;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.PerformanceService;
import at.ac.tuwien.inso.tl.server.service.ShowService;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/shows")
public class ShowController
{
	private static final Logger LOG = Logger.getLogger(ShowController.class);

	@Autowired
	private ShowService showService;

	@Autowired
	private PerformanceService performanceService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ShowDto> findShows(@RequestParam(value = "search", required = false) String search)
			throws ServiceException
	{
		LOG.info("findShows called. search for: " + search);

		return EntityToDto.convertShows(this.showService.findShows(search));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ShowDto getShowById(@PathVariable("id") Integer id) throws ServiceException
	{
		LOG.info("getShow() called");
		return EntityToDto.convert(showService.getShow(id));
	}

	@RequestMapping(value = "/{id}/performances", method = RequestMethod.GET, produces = "application/json")
	public List<PerformanceDto> getPerformancesByShowId(@PathVariable("id") Integer id)
			throws ServiceException
	{
		LOG.info("getShowsByArtistId called. search for: " + id);
		return EntityToDto.convertPerformances(this.performanceService.getPerformancesByShowId(id));
	}
}
