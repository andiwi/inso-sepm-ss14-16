package at.ac.tuwien.inso.tl.server.unittest.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import at.ac.tuwien.inso.tl.dao.NewsDao;
import at.ac.tuwien.inso.tl.model.News;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.impl.NewsServiceImpl;

public class NewsServiceTest {
	NewsServiceImpl service = null;
	
	List<News> news = null;
	
	@Before
	public void setUp(){
		service = new NewsServiceImpl();
		news = new ArrayList<News>();
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(2013, 5, 1);
		News n = new News();
		n.setId(1);
		n.setCreatedOn(gc.getTime());
		n.setTitle("first Title");
		n.setDescription("first newstext");
		news.add(n);

		n = new News();
		n.setId(2);
		n.setCreatedOn(gc.getTime());
		n.setTitle("Second Title");
		n.setDescription("Hudel Dudel");
		news.add(n);

		gc.set(2013, 7, 4);
		n = new News();
		n.setId(3);
		n.setCreatedOn(gc.getTime());
		n.setTitle("Bi Ba");
		n.setDescription("Fischers Fritz fischt frische Fische");
		news.add(n);

		n = new News();
		n.setId(4);
		n.setCreatedOn(gc.getTime());
		n.setTitle("Änderungsmitteilung");
		n.setDescription("Alles muss raus");
		news.add(n);

		gc.set(2013, 8, 15);
		n = new News();
		n.setId(5);
		n.setCreatedOn(gc.getTime());
		n.setTitle("It's the last News");
		n.setDescription("Nix neues im Westen");
		news.add(n);

	}
	
	@Test
	public void testGetAll(){
		NewsDao dao =Mockito.mock(NewsDao.class);
		Mockito.when(dao.findAllOrderByCreatedOnDesc()).thenReturn(news);
		service.setNewsDao(dao);
		
		try {
			assertEquals(5, service.getAllNews().size());
		} catch (ServiceException e) {
			fail("ServiceException thrown");
		}
	}
	
	@Test
	public void testGetAll_shouldThrowServiceException(){
		NewsDao dao =Mockito.mock(NewsDao.class);
		Mockito.when(dao.findAllOrderByCreatedOnDesc()).thenThrow(new RuntimeException("no db"));
		service.setNewsDao(dao);
		
		try {
			service.getAllNews();
			fail("ServiceException not thrown");
		} catch (ServiceException e) {
			assertNotNull(e.getMessage());
		}
	}
}
