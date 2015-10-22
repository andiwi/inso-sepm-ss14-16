package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.model.News;
import at.ac.tuwien.inso.tl.server.service.NewsService;

public class NewsServiceIntegrationTest extends AbstractServiceIntegrationTest{
	@Autowired
	NewsService service;
	
	@Before
	public void setUp(){
	}
	
	@Test
	public void testGetAll() throws ServiceException {
		assertThat(this.service.getAllNews(), hasSize(10));
	}
	
	@Test
	public void testGetAll_CheckOrder() throws ServiceException {
		List<News> news = service.getAllNews();

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
}
