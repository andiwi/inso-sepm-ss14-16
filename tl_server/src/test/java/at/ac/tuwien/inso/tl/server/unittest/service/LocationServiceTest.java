package at.ac.tuwien.inso.tl.server.unittest.service;

import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import at.ac.tuwien.inso.tl.dao.LocationDao;
import at.ac.tuwien.inso.tl.model.Location;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.impl.LocationServiceImpl;

public class LocationServiceTest {

	private LocationServiceImpl locationServiceImpl;
	
	private List<Location> locations;
	
	@Before
	public void setUp(){
		
		this.locations = new ArrayList<Location>();

		Location l = new Location();
		l.setId(1);
		l.setTitle("Wiener Staatsoper");
		l.setDescription("Die Wiener Staatsoper, das „Erste Haus am Ring“, ist eines der bekanntesten Opernhaeuser der Welt und befindet sich im 1. Wiener Gemeindebezirk Innere Stadt. Sie wurde am 25. Mai 1869 mit einer Premiere von Don Giovanni von Mozart eroeffnet. Aus den Mitgliedern des Staatsopernorchesters rekrutieren sich u. a. die Wiener Philharmoniker. Der Chor der Wiener Staatsoper tritt extern als Konzertvereinigung Wiener Staatsopernchor auf.");
		l.setStreet("Opernring 2");
		l.setPostcode("1010");
		l.setCity("Wien");
		l.setCountry("Oesterreich");
		this.locations.add(l);
		
		l = new Location();
		l.setId(2);
		l.setTitle("Grosses Festspielhaus");
		l.setDescription("Das Grosse Festspielhaus in Salzburg (von 1960 bis 1962 Neues Festspielhaus, seit 1963 Grosses Festspielhaus) ist eine der Spielstaetten der Salzburger Festspiele und befindet sich in der Altstadt, es ist teilweise in den Moenchsberg hinein gebaut.");
		l.setStreet("Hofstallgasse 1");
		l.setPostcode("5020");
		l.setCity("Salzburg");
		l.setCountry("Oesterreich");
		this.locations.add(l);

		l = new Location();
		l.setId(3);
		l.setTitle("Flex");
		l.setDescription("Das Flex in Wien ist ein zwischen der U-Bahn-Station Schottenring und der Augartenbruecke gelegener Musikclub und in dieser Funktion Schauplatz von Auftritten lokaler und internationaler Musikgruppen und DJs.");
		l.setStreet("Augartenbruecke 1");
		l.setPostcode("1010");
		l.setCity("Wien");
		l.setCountry("Oesterreich");
		this.locations.add(l);
		
		this.locationServiceImpl = new LocationServiceImpl();
		LocationDao dao = Mockito.mock(LocationDao.class);
		Mockito.when(dao.findOne(anyInt())).thenAnswer(new Answer<Location>() {
			public Location answer(InvocationOnMock invocation) throws ServiceException {
				Object[] args = invocation.getArguments();
				Integer arg = (Integer) args[0];
				if (arg == null) {
					throw new ServiceException();
				}
				if (arg == 100) {
					return null;
				}
				return LocationServiceTest.this.locations.get(arg - 1);
			}
		});
		Mockito.when(dao.findLocationLike(anyString())).thenAnswer(new Answer<List<Location>>() {
			public List<Location> answer(InvocationOnMock invocation) throws ServiceException {
				Object[] args = invocation.getArguments();
				String arg = (String) args[0];
				ArrayList<Location> ls = new ArrayList<Location>();
				if (arg.equals("staatsopE".toUpperCase())) {
					ls.add(LocationServiceTest.this.locations.get(0));
				}
				if (arg.equals("ugarte".toUpperCase())) {
					ls.add(LocationServiceTest.this.locations.get(2));
				}
				if (arg.equals("1010".toUpperCase())) {
					ls.add(LocationServiceTest.this.locations.get(0));
					ls.add(LocationServiceTest.this.locations.get(2));
				}
				if (arg.equals("alzbuR".toUpperCase())) {
					ls.add(LocationServiceTest.this.locations.get(1));
				}
				if (arg.equals("oesterre".toUpperCase())) {
					return LocationServiceTest.this.locations;
				}
				return ls;
			}
		});
		Mockito.when(dao.findAll()).thenReturn(this.locations);
		this.locationServiceImpl.setLocationDao(dao);
	}
	
	@Test(expected=ServiceException.class)
	public void getLocation_throwsExceptionIfIdIsNull() throws ServiceException {
		this.locationServiceImpl.getLocation(null);
	}
	
	@Test
	public void getLocation_returnsNullIfNotFound() throws ServiceException {
		Location l = this.locationServiceImpl.getLocation(100);
		
		assertThat(l, is(nullValue()));
	}
	
	@Test
	public void getLocation_returnsCorrectLocation() throws ServiceException {
		Location actual = this.locationServiceImpl.getLocation(2);
		
		Location expected = this.locations.get(1);
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void findLocations_returnsAllLocationsIfSearchStringIsEmpty() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations("");
		
		List<Location> expected = this.locations;
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void findLocations_returnsAllLocationsIfSearchStringIsNull() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations(null);
		
		List<Location> expected = this.locations;
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void findLocations_findsLocationsByTitle() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations("staatsopE");
		
		Location expected = this.locations.get(0);
		
		assertThat(actual, hasSize(1));
		assertThat(actual.get(0), is(expected));
	}
	
	@Test
	public void findLocations_findsLocationsByStreet() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations("ugarte");
		
		Location expected = this.locations.get(2);
		
		assertThat(actual, hasSize(1));
		assertThat(actual.get(0), is(expected));
	}
	
	@Test
	public void findLocations_findsLocationsByPostcode() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations("1010");

		Location expected1 = this.locations.get(0);
		Location expected2 = this.locations.get(2);
		
		assertThat(actual, hasSize(2));
		assertThat(actual, containsInAnyOrder(expected1, expected2));
	}
	
	@Test
	public void findLocations_findsLocationsByCity() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations("alzbuR");
		
		Location expected = this.locations.get(1);
		
		assertThat(actual, hasSize(1));
		assertThat(actual.get(0), is(expected));
	}
	
	@Test
	public void findLocations_findsLocationsByCountry() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations("oesterre");
		
		List<Location> expected = this.locations;
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void findLocations_doesNotFindLocationsByDescription() throws ServiceException {
		List<Location> actual = this.locationServiceImpl.findLocations("Gemeindebezirk");
		
		assertThat(actual, is(empty()));
	}
	
}
