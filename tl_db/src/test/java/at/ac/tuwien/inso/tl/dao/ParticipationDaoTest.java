package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Participation;

public class ParticipationDaoTest extends AbstractDaoTest
{
	@Autowired
	private ParticipationDao dao;
	
	@Test
	public void testFindAll(){
		List<Participation> participations = dao.findAll();
		assertEquals("Check DB initial data - is two first", 2, participations.size()); 
	}
}
