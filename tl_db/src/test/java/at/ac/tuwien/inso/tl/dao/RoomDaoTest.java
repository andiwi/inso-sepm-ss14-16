package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Room;

public class RoomDaoTest extends AbstractDaoTest
{
	@Autowired
	private RoomDao dao;
	
	@Test
	public void testFindAll(){
		List<Room> rooms = dao.findAll();
		assertEquals("Check DB initial data - is four first", 4, rooms.size()); 
	}
}
