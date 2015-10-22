package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Ticket;

public class TicketDaoTest extends AbstractDaoTest
{
	@Autowired
	private TicketDao dao;
	
	@Test
	public void testFindAll(){
		List<Ticket> tickets = dao.findAll();
		assertEquals("Check DB initial data - is two first", 2, tickets.size()); 
	}
}
