package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import at.ac.tuwien.inso.tl.dao.NewsDao;
import at.ac.tuwien.inso.tl.model.News;

public class NewsDaoTest extends AbstractDaoTest{
	@Autowired
	private NewsDao ndao;
	
	@Test
	public void testFindAll(){
		List<News> news = ndao.findAll();
		assertEquals("Check DB initial data - is ten first", 10, news.size()); 
	}
	
	@Test
	public void testFindAllOrderBySubmittedOnDesc_CheckOrder(){
		List<News> news = ndao.findAllOrderByCreatedOnDesc();

		assertEquals(10, news.size());
		
		Date date = news.get(0).getCreatedOn();
		assertTrue(!date.before(news.get(1).getCreatedOn()));
		
		date = news.get(1).getCreatedOn();
		assertTrue(!date.before(news.get(2).getCreatedOn()));
		
		date = news.get(2).getCreatedOn();
		assertTrue(!date.before(news.get(3).getCreatedOn()));
		
		date = news.get(3).getCreatedOn();
		assertTrue(!date.before(news.get(4).getCreatedOn()));
		
		date = news.get(4).getCreatedOn();
		assertTrue(!date.before(news.get(5).getCreatedOn()));
		
		date = news.get(5).getCreatedOn();
		assertTrue(!date.before(news.get(6).getCreatedOn()));
		
		date = news.get(6).getCreatedOn();
		assertTrue(!date.before(news.get(7).getCreatedOn()));
		
		date = news.get(7).getCreatedOn();
		assertTrue(!date.before(news.get(8).getCreatedOn()));
		
		date = news.get(8).getCreatedOn();
		assertTrue(!date.before(news.get(9).getCreatedOn()));
	}
	
	@Test
	public void testFindOneById(){
		News n = ndao.findOne(6);
		assertEquals(6, n.getId().longValue());
		assertEquals("Ein Sommernachtstraum", n.getTitle());
	}
	
	@Test
	public void testFindOneById_NegativId(){
		assertNull(ndao.findOne(-1));
	}
	
	@Test
	public void testFindOneById_InvalidId(){
		assertNull(ndao.findOne(0));
	}
	
	@Test
	public void testAddNews(){
		assertEquals("Check DB initial data - is ten first", 10, ndao.count());
		News n = new News();
		n.setTitle("NewsDao test");
		n.setDescription("test text,test text,test text,test text,test text,test text");
		n.setCreatedOn(new GregorianCalendar().getTime());
		
		News saved = ndao.save(n);
		assertEquals("Check News count - should be 11", 11, ndao.count());
		
		n= null;
		n = ndao.findOne(saved.getId());
		assertNotNull(n);
	}
	
	@Test
	public void testAddNews_shouldThrowException_TitleNull(){
		assertEquals("Check DB initial data - is ten first", 10, ndao.count());
		News n = new News();
		n.setDescription("test text,test text,test text,test text,test text,test text");
		n.setCreatedOn(new GregorianCalendar().getTime());
		try {
			ndao.save(n);
			fail("DataIntegrityViolationException not thrown");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void testAddNews_shouldThrowException_NewsTextNull(){
		assertEquals("Check DB initial data - is ten first", 10, ndao.count());
		News n = new News();
		n.setTitle("NewsDao test");
		n.setCreatedOn(new GregorianCalendar().getTime());
		try {
			ndao.save(n);
			fail("DataIntegrityViolationException not thrown");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteNews(){
		assertEquals("Check DB initial data - is ten first", 10, ndao.count());
		ndao.delete(1);

		assertEquals("Check News count - should be 9", 9, ndao.count());
	}
	
	@Test
	public void testDeleteNews_shouldThrowException_InvalidId(){
		assertEquals("Check DB initial data - is ten first", 10, ndao.count());
		try {
			ndao.delete(-1);
			fail("EmptyResultDataAccessException not thrown");
		} catch (EmptyResultDataAccessException e) {
			assertNotNull(e.getMessage());
		}
	}

}
