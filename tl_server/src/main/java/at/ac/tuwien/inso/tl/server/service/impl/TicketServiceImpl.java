package at.ac.tuwien.inso.tl.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.TicketDao;
import at.ac.tuwien.inso.tl.model.Ticket;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService
{

	@Autowired
	private TicketDao ticketDao;

	public List<Ticket> getAllTickets() throws ServiceException
	{
		try
		{
			return this.ticketDao.getAllTickets();
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

}
