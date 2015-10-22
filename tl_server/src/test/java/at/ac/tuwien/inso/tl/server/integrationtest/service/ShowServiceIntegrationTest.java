package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Show;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ShowService;

public class ShowServiceIntegrationTest extends AbstractServiceIntegrationTest
{
	@Autowired
	private ShowService showService;
	
	@Before
	public void setUp(){}

	@Test
	public void findById_returnsNullIfNotExists() throws ServiceException {
		assertThat(this.showService.getShow(100), is(nullValue()));
	}

	@Test
	public void findById_returnsNullIfIdIsNull() throws ServiceException {
		assertThat(this.showService.getShow(null), is(nullValue()));
	}

	@Test
	public void findById_returnsShowIfExists() throws ServiceException {
		Show a = this.showService.getShow(1); 
		assertThat(a.getId(), is(1));
	}

	@Test
	public void findShows_returnsAllIfSearchIsNull() throws ServiceException { 
		assertThat(this.showService.findShows(null), hasSize(3));
	}

	@Test
	public void findShows_returnsAllIfSearchIsEmpty() throws ServiceException { 
		assertThat(this.showService.findShows(""), hasSize(3));
	}

	@Test
	public void findShows_searchesInTitle() throws ServiceException { 
		assertThat(this.showService.findShows("Great"), hasSize(1));
	}

	@Test
	public void findShows_searchesInDescription() throws ServiceException { 
		assertThat(this.showService.findShows("Foo"), hasSize(2));
	}

	@Test
	public void findShows_searchesInDescriptionCaseInsensitive() throws ServiceException { 
		assertThat(this.showService.findShows("FooB"), hasSize(1));
	}

	@Test
	public void findShows_searchesInTitleCaseInsensitive() throws ServiceException { 
		assertThat(this.showService.findShows("AwESo"), hasSize(1));
	} 

	@Test
	public void getShowsByArtistId_returnsEmptyListIfArtistIdIsNull() throws ServiceException { 
		assertThat(this.showService.getShowsByArtistId(null), hasSize(0));
	}  

	@Test
	public void getShowsByArtistId_returnsEmptyListIfArtistDoesNotExist() throws ServiceException { 
		assertThat(this.showService.getShowsByArtistId(100), hasSize(0));
	}   

	@Test
	public void getShowsByArtistId_returnsCorrectShows() throws ServiceException { 
		assertThat(this.showService.getShowsByArtistId(1), hasSize(2)); 
		assertThat(this.showService.getShowsByArtistId(6), hasSize(1));
	} 
}
