package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Reservation;

public class ReservationDaoTest extends AbstractDaoTest
{
	@Autowired
	private ReservationDao dao;
	
	@Test
	public void testFindAll(){
		List<Reservation> reservations = dao.findAll();
		assertEquals("Check DB initial data - is two first", 2, reservations.size()); 
	}
	
	@Test
	public void findReservationLike()
	{
		List<Reservation> rList = dao.findReservationLike("ANDREAS");
		assertEquals("Should be one", 1, rList.size());
	}
	
	@Test
	public void findByIdWithRelatedEntities()
	{
		Reservation r = dao.findByIdWithRelatedEntities(2);
		assertNotNull(r.getCustomer());
		assertNotNull(r.getTickets());
	}
}
