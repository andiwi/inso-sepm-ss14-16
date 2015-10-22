package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Location;

public class LocationDaoTest extends AbstractDaoTest
{
	@Autowired
	private LocationDao dao;
	
	@Test
	public void testFindAll(){
		List<Location> locations = dao.findAll();
		assertEquals("Check DB initial data - is three first", 3, locations.size()); 
	}
	
	@Test
	public void findLocationLike()
	{
		List<Location> locations = dao.findLocationLike("FLEX");
		assertEquals("Should have found one location", 1, locations.size());
	}
}
