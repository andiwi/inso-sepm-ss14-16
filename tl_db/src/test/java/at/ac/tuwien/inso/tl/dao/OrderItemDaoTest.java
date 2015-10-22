package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.OrderItem;

public class OrderItemDaoTest extends AbstractDaoTest
{
	@Autowired
	private OrderItemDao dao;
	
	@Test
	public void testFindAll(){
		List<OrderItem> orderItems = dao.findAll();
		assertEquals("Check DB initial data - is two first", 2, orderItems.size()); 
	}
}
