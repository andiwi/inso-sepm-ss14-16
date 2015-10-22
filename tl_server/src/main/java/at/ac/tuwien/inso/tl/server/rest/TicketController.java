package at.ac.tuwien.inso.tl.server.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.ShowDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.TicketService;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController
{
	private static final Logger LOG = Logger.getLogger(TicketController.class);

	@Autowired
	private TicketService ticketService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<TicketDto> getAllTickets() throws ServiceException
	{
		LOG.info("getAllTickets");

		return EntityToDto.convertTicketsWithPerformance(this.ticketService.getAllTickets());
	}
}
