package at.ac.tuwien.inso.tl.server.rest;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.MessageDto;
import at.ac.tuwien.inso.tl.dto.MessageType;
import at.ac.tuwien.inso.tl.dto.NewsDto;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.server.exception.OrderException;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.OrderService;
import at.ac.tuwien.inso.tl.server.util.DtoToEntity;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/orders")
public class OrderController
{
	private static final Logger LOG = Logger.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<OrderDto> findOrders(@RequestParam(value = "search", required = false) String search)
			throws ServiceException
	{
		return EntityToDto.convertOrders(this.orderService.findOrders(search));
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public MessageDto newOrder(@Valid @RequestBody OrderDto order) throws ServiceException
	{
		LOG.info("newOrder() called");

		Integer id = this.orderService.save(order).getId();

		MessageDto msg = new MessageDto();
		msg.setType(MessageType.SUCCESS);
		msg.setText(id.toString());

		return msg;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public OrderDto getOrderById(@PathVariable("id") Integer id) throws ServiceException
	{
		return EntityToDto.convertWithItems(this.orderService.getOrder(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public void update(@PathVariable("id") Integer id, @Valid @RequestBody OrderDto order) throws ServiceException
	{
		this.orderService.update(order);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void cancel(@PathVariable("id") Integer id) throws ServiceException
	{
		this.orderService.delete(id);
	}
}
