package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import at.ac.tuwien.inso.tl.model.Performance;

public class PerformanceDaoTest extends AbstractDaoTest {
	@Autowired
	PerformanceDao dao;
	
	@Test
	public void findByLocationId_includesRoomAndShowTitles() {
		List<Performance> ps = this.dao.findByLocationId(1);

		assertThat(ps.get(0).getShow(), is(notNullValue()));
		assertThat(ps.get(0).getShow().getTitle(), is(notNullValue()));
		assertThat(ps.get(0).getRoom(), is(notNullValue()));
		assertThat(ps.get(0).getRoom().getTitle(), is(notNullValue()));
	}
	
	@Test
	public void findByLocationId()
	{
		List<Performance> performance = dao.findByLocationId(2);
		assertEquals("Should have found one", 1, performance.size());
	}
	
	@Test
	public void findById()
	{
		Performance p = dao.findById(1);
		assertNotNull(p);
		assertNotNull(p.getRoom());
		assertNotNull(p.getShow());
		assertNotNull(p.getTickets());
	}
	
	@Test
	public void findByArtistId()
	{
		List<Performance> pList = dao.findByArtistId(1);
		assertEquals("Should be six", 6, pList.size());
	}
	
	@Test
	public void findByShowId()
	{
		List<Performance> pList = dao.findByShowId(1);
		assertEquals("Should be six", 6, pList.size());
	}
}
