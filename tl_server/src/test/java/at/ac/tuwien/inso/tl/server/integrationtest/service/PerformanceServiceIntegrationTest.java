package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.PerformanceService;

public class PerformanceServiceIntegrationTest extends AbstractServiceIntegrationTest
{
	@Autowired
	private PerformanceService performanceService;
	
	@Before
	public void setUp(){}

	@Test
	public void findById_returnsNullIfNotExists() throws ServiceException {
		assertThat(this.performanceService.getPerformance(100), is(nullValue()));
	}

	@Test
	public void findById_returnsNullIfIdIsNull() throws ServiceException {
		assertThat(this.performanceService.getPerformance(null), is(nullValue()));
	}

	@Test
	public void findById_returnsPerformanceIfExists() throws ServiceException {
		Performance a = this.performanceService.getPerformance(1); 
		assertThat(a.getId(), is(1));
	}

	@Test
	public void getPerformancesByShowId_returnsEmptyListIfShowIdIsNull() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByShowId(null), hasSize(0));
	}  

	@Test
	public void getPerformancesByShowId_returnsEmptyListIfShowDoesNotExist() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByShowId(100), hasSize(0));
	}   

	@Test
	public void getPerformancesByShowId_returnsCorrectPerformances() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByShowId(1), hasSize(3)); 
		assertThat(this.performanceService.getPerformancesByShowId(2), hasSize(2)); 
		assertThat(this.performanceService.getPerformancesByShowId(3), hasSize(1));
	} 

	@Test
	public void getPerformancesByLocationId_returnsEmptyListIfLocationIdIsNull() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByLocationId(null), hasSize(0));
	}  

	@Test
	public void getPerformancesByLocationId_returnsEmptyListIfLocationDoesNotExist() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByLocationId(100), hasSize(0));
	}   

	@Test
	public void getPerformancesByLocationId_returnsCorrectPerformances() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByLocationId(1), hasSize(2)); 
		assertThat(this.performanceService.getPerformancesByLocationId(2), hasSize(1)); 
		assertThat(this.performanceService.getPerformancesByLocationId(3), hasSize(3));
	} 

	@Test
	public void getPerformancesByArtistId_returnsEmptyListIfArtistIdIsNull() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByArtistId(null), hasSize(0));
	}  

	@Test
	public void getPerformancesByArtistId_returnsEmptyListIfArtistDoesNotExist() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByArtistId(100), hasSize(0));
	}   

	@Test
	public void getPerformancesByArtistId_returnsCorrectPerformances() throws ServiceException { 
		assertThat(this.performanceService.getPerformancesByArtistId(1), hasSize(5));
		assertThat(this.performanceService.getPerformancesByArtistId(6), hasSize(3));
	} 
}
