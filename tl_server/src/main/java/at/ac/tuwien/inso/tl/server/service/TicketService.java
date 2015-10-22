package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.model.Ticket;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface TicketService
{

	/**
	 * Returns a list of all Tickets
	 * 
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Ticket> getAllTickets() throws ServiceException;

}
