package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

public interface ReservationService
{

	public List<ReservationDto> findReservations(String search) throws ServiceException;

	public ReservationDto reserveTickets(List<TicketDto> tickets, CustomerDto customer)
			throws ServiceException;

	public ReservationDto getReservationById(ReservationDto reservation) throws ServiceException;

	public ReservationDto update(ReservationDto reservation, List<TicketDto> tickets,
			CustomerDto customer) throws ServiceException;

	public void cancel(ReservationDto reservation) throws ServiceException;
}
