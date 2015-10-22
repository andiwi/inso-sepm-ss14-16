package at.ac.tuwien.inso.tl.server.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.MessageDto;
import at.ac.tuwien.inso.tl.dto.MessageType;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.OrderService;
import at.ac.tuwien.inso.tl.server.service.ReservationService;
import at.ac.tuwien.inso.tl.server.util.DtoToEntity;
import at.ac.tuwien.inso.tl.server.util.EntityToDto;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController
{

	private static final Logger LOG = Logger.getLogger(ReservationController.class);

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public MessageDto createReservation(@Valid @RequestBody ReservationDto reservation)
			throws ServiceException
	{
		LOG.info("createReservation called");

		Integer id = this.reservationService.save(reservation).getId();

		MessageDto msg = new MessageDto();
		msg.setType(MessageType.SUCCESS);
		msg.setText(id.toString());

		return msg;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ReservationDto> findReservations(
			@RequestParam(value = "search", required = false) String search)
			throws ServiceException
	{
		return EntityToDto.convertReservations(this.reservationService.findReservations(search));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ReservationDto getReservationById(@PathVariable("id") Integer id) throws ServiceException
	{
		return EntityToDto.convertWithTickets(this.reservationService.getReservation(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public void update(@PathVariable("id") Integer id, @Valid @RequestBody ReservationDto reservation) throws ServiceException
	{
		this.reservationService.update(reservation);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void cancel(@PathVariable("id") Integer id) throws ServiceException
	{
		this.reservationService.delete(id);
	}
}
