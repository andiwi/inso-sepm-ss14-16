package at.ac.tuwien.inso.tl.server.integrationtest.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;
import at.ac.tuwien.inso.tl.model.Reservation;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ReservationService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReservationServiceIntegrationTest extends AbstractServiceIntegrationTest
{

	@Autowired
	private ReservationService reservationService;
	
	@Test(expected=ServiceException.class)
	public void save_throwsExceptionIfNoTickets() throws ServiceException {
		ReservationDto o = new ReservationDto();
		CustomerDto c = new CustomerDto();
		c.setId(1);
		o.setCustomer(c);
		this.reservationService.save(o);
	}

	@Test(expected=ServiceException.class)
	public void save_throwsExceptionIfNoCustomer() throws ServiceException {
		ReservationDto o = new ReservationDto();
		List<TicketDto> ts = new ArrayList<TicketDto>();
		ts.add(new TicketDto());
		o.setTickets(ts);
		this.reservationService.save(o);
	}
	
	@Test
	public void update_updatesCustomer() throws ServiceException {
		Reservation r = this.reservationService.getReservation(4);
		assertThat(r.getCustomer().getId(), is(5));
		
		ReservationDto rdto = new ReservationDto();
		rdto.setId(r.getId());
		CustomerDto cdto = new CustomerDto();
		cdto.setId(2);
		rdto.setCustomer(cdto);
		
		this.reservationService.update(rdto);
		r = this.reservationService.getReservation(4);
		assertThat(r.getCustomer().getId(), is(2));
	}
	
	@Test
	public void delete_deletesReservation() throws ServiceException {
		Reservation o = this.reservationService.getReservation(3);
		assertThat(o, instanceOf(Reservation.class));
		
		this.reservationService.delete(3);
		o = this.reservationService.getReservation(3);
		assertThat(o, is(nullValue()));
	}

	@Test
	public void getReservation_returnsNullIfNotExists() throws ServiceException {
		assertThat(this.reservationService.getReservation(100), is(nullValue()));
	}

	@Test
	public void getReservation_returnsNullIfIdIsNull() throws ServiceException {
		assertThat(this.reservationService.getReservation(null), is(nullValue()));
	}

	@Test
	public void getReservation_returnsShowIfExists() throws ServiceException {
		Reservation o = this.reservationService.getReservation(1); 
		assertThat(o.getId(), is(1));
	}

	@Test
	public void findReservations_returnsAllIfSearchIsNull() throws ServiceException { 
		assertThat(this.reservationService.findReservations(null), hasSize(4));
	}

	@Test
	public void findReservations_returnsAllIfSearchIsEmpty() throws ServiceException { 
		assertThat(this.reservationService.findReservations(""), hasSize(3));
	}

	@Test
	public void findReservations_searchesInFirstName() throws ServiceException { 
		assertThat(this.reservationService.findReservations("est"), hasSize(1));
	}

	@Test
	public void findReservations_searchesInLastName() throws ServiceException { 
		assertThat(this.reservationService.findReservations("oo"), hasSize(1));
	}

	@Test
	public void findReservations_searchesInFirstNameCaseInsensitive() throws ServiceException { 
		assertThat(this.reservationService.findReservations("EsTi"), hasSize(1));
	}

	@Test
	public void findReservations_searchesInLastNameCaseInsensitive() throws ServiceException { 
		assertThat(this.reservationService.findReservations("WiTTMa"), hasSize(1));
	} 

	@Test
	public void findReservations_searchesInCustomerNumber() throws ServiceException { 
		assertThat(this.reservationService.findReservations("c"), hasSize(1));
	} 
}
