package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Show;

public class ShowDaoTest extends AbstractDaoTest
{
	@Autowired
	private ShowDao dao;
	
	@Test
	public void testFindAll(){
		List<Show> shows = dao.findAll();
		assertEquals("Check DB initial data - is one first", 1, shows.size()); 
	}
}
