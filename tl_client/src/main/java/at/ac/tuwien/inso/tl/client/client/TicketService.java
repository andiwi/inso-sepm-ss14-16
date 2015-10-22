package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.TicketDto;

public interface TicketService
{

	public List<TicketDto> getAllTickets() throws ServiceException;
}
