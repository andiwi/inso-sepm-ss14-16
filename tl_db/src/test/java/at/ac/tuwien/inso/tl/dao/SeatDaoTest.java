package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Seat;

public class SeatDaoTest extends AbstractDaoTest
{
	@Autowired
	private SeatDao dao;
	
	@Test
	public void testFindAll(){
		List<Seat> seats = dao.findAll();
		assertEquals("Check DB initial data - is two first", 2, seats.size()); 
	}
}
