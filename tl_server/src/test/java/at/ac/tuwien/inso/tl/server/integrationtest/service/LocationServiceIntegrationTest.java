package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Location;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.LocationService;

public class LocationServiceIntegrationTest extends AbstractServiceIntegrationTest {
	
	@Autowired
	private LocationService locationService;
	
	@Before
	public void setUp(){}
	
	@Test(expected=ServiceException.class)
	public void getLocation_throwsExceptionIfIdIsNull() throws ServiceException {
		this.locationService.getLocation(null);
	}
	
	@Test
	public void getLocation_returnsNullIfNotFound() throws ServiceException {
		Location l = this.locationService.getLocation(100);
		
		assertThat(l, is(nullValue()));
	}
	
	@Test
	public void getLocation_returnsCorrectLocation() throws ServiceException {
		Location actual = this.locationService.getLocation(2);
		assertThat(actual, hasProperty("title", is("Grosses Festspielhaus")));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void findLocations_returnsAllLocationsIfSearchStringIsEmpty() throws ServiceException {
		List<Location> actual = this.locationService.findLocations("");

		assertThat(actual, hasSize(3));
		assertThat(actual, containsInAnyOrder(hasProperty("title", is("Wiener Staatsoper")), hasProperty("title", is("Grosses Festspielhaus")), hasProperty("title", is("Flex"))));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void findLocations_returnsAllLocationsIfSearchStringIsNull() throws ServiceException {
		List<Location> actual = this.locationService.findLocations(null);

		assertThat(actual, hasSize(3));
		assertThat(actual, containsInAnyOrder(hasProperty("title", is("Wiener Staatsoper")), hasProperty("title", is("Grosses Festspielhaus")), hasProperty("title", is("Flex"))));
	}
	
	@Test
	public void findLocations_findsLocationsByTitle() throws ServiceException {
		List<Location> actual = this.locationService.findLocations("staatsopE");
		
		assertThat(actual, hasSize(1));
		assertThat(actual, contains(hasProperty("title", is("Wiener Staatsoper"))));
	}
	
	@Test
	public void findLocations_findsLocationsByStreet() throws ServiceException {
		List<Location> actual = this.locationService.findLocations("ugarte");
		
		assertThat(actual, hasSize(1));
		assertThat(actual, contains(hasProperty("title", is("Flex"))));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void findLocations_findsLocationsByPostcode() throws ServiceException {
		List<Location> actual = this.locationService.findLocations("1010");
		
		assertThat(actual, hasSize(2));
		assertThat(actual, containsInAnyOrder(hasProperty("title", is("Wiener Staatsoper")), hasProperty("title", is("Flex"))));
	}
	
	@Test
	public void findLocations_findsLocationsByCity() throws ServiceException {
		List<Location> actual = this.locationService.findLocations("alzbuR");
		
		assertThat(actual, hasSize(1));
		assertThat(actual, contains(hasProperty("title", is("Grosses Festspielhaus"))));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void findLocations_findsLocationsByCountry() throws ServiceException {
		List<Location> actual = this.locationService.findLocations("oesterre");

		assertThat(actual, hasSize(3));
		assertThat(actual, containsInAnyOrder(hasProperty("title", is("Wiener Staatsoper")), hasProperty("title", is("Grosses Festspielhaus")), hasProperty("title", is("Flex"))));
	}
	
	@Test
	public void findLocations_doesNotFindLocationsByDescription() throws ServiceException {
		List<Location> actual = this.locationService.findLocations("Gemeindebezirk");
		
		assertThat(actual, is(empty()));
	}
	
}
