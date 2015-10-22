package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Order;

public class OrderDaoTest extends AbstractDaoTest
{
	@Autowired
	private OrderDao dao;
	
	@Test
	public void testFindAll(){
		List<Order> orders = dao.findAll();
		assertEquals("Check DB initial data - is two first", 2, orders.size()); 
	}
	
	@Test
	public void findOrderLike()
	{
		List<Order> orders = dao.findOrderLike("ANDREAS");
		assertEquals("Should have found one orders", 1, orders.size());
	}
	
	@Test
	public void findByIdWithRelatedEntities()
	{
		Order order = dao.findByIdWithRelatedEntities(1);
		assertNotNull(order.getOrderItems());
	}
}
