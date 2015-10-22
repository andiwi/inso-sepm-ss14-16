package at.ac.tuwien.inso.tl.server.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.LocationDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.LocationService;
import at.ac.tuwien.inso.tl.server.service.PerformanceService;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/locations")
public class LocationController
{
	private static final Logger LOG = Logger.getLogger(LocationController.class);
	@Autowired
	private LocationService locationService;

	@Autowired
	private PerformanceService performanceService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<LocationDto> findLocations(
			@RequestParam(value = "search", required = false) String search)
			throws ServiceException
	{

		if (search != null)
		{
			try
			{
				search = URLDecoder.decode(search, "UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return EntityToDto.convertLocations(this.locationService.findLocations(search));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public LocationDto getLocationById(@PathVariable("id") Integer id) throws ServiceException
	{
		return EntityToDto.convert(this.locationService.getLocation(id));
	}

	@RequestMapping(value = "/{id}/performances", method = RequestMethod.GET, produces = "application/json")
	public List<PerformanceDto> getPerformancesByLocationId(@PathVariable("id") Integer id)
			throws ServiceException
	{
		return EntityToDto.convertPerformances(this.performanceService
				.getPerformancesByLocationId(id));
	}
}
