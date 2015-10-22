package at.ac.tuwien.inso.tl.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.PerformanceService;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/performances")
public class PerformanceController
{

	@Autowired
	private PerformanceService performanceService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public PerformanceDto getPerformanceById(@PathVariable("id") Integer id)
			throws ServiceException
	{
		return EntityToDto.convertWithTickets(this.performanceService.getPerformance(id));
	}

}
