package at.ac.tuwien.inso.tl.client.client;

import java.util.List;
import java.util.Set;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.MethodOfPayment;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

public interface OrderService
{
	public OrderDto getOrderById(OrderDto order) throws ServiceException;

	public List<OrderDto> findOrders(String search) throws ServiceException;

	public OrderDto update(
			OrderDto order,
			List<TicketDto> tickets,
			List<ArticleDto> articles,
			CustomerDto customer,
			MethodOfPayment methodOfPayment) throws ServiceException;

	public void cancel(OrderDto order) throws ServiceException;

	public OrderDto orderTicketsAndArticles(
			List<TicketDto> tickets,
			List<ArticleDto> articles,
			CustomerDto customer,
			MethodOfPayment methodOfPayment,
			Boolean useBonusPoints) throws ServiceException;

	public OrderDto orderReservationAndArticles(
			List<TicketDto> tickets,
			List<ArticleDto> articles,
			ReservationDto reservation,
			CustomerDto customer,
			MethodOfPayment methodOfPayment,
			Boolean useBonusPoints) throws ServiceException;
}
