package at.ac.tuwien.inso.tl.server.unittest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import at.ac.tuwien.inso.tl.dao.ArtistDao;
import at.ac.tuwien.inso.tl.dao.LocationDao;
import at.ac.tuwien.inso.tl.model.Artist;
import at.ac.tuwien.inso.tl.model.Location;
import at.ac.tuwien.inso.tl.model.Participation;
import at.ac.tuwien.inso.tl.server.exception.ArtistException;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.impl.ArtistServiceImpl;

public class ArtistServiceTest
{

	private ArtistServiceImpl artistServiceImpl;
	
	private List<Artist> artists;
	
	@Before
	public void setUp()
	{
		this.artists = new ArrayList<Artist>();
				
		List<Participation> participations = new ArrayList<Participation>();
		Participation p1 = new Participation();
		p1.setId(1);
		
		Participation p2 = new Participation();
		p2.setId(2);
		
		participations.add(p1);
		participations.add(p2);
		
		
		Artist a1 = new Artist();
		a1.setId(1);
		a1.setFirstname("Robbie");
		a1.setLastname("Williams");
		a1.setDescription("D1");
		a1.setParticipations(participations);
		
		artists.add(a1);
		
		Artist a2 = new Artist();
		a1.setId(2);
		a1.setFirstname("F2");
		a1.setLastname("Williams");
		a1.setDescription("D2");
		a1.setParticipations(participations);
		
		artists.add(a2);
		
		this.artistServiceImpl = new ArtistServiceImpl();
		ArtistDao dao = Mockito.mock(ArtistDao.class);
		
		Mockito.when(dao.findByIdWithParticipations(anyInt())).thenAnswer(new Answer<Artist>() {
			public Artist answer(InvocationOnMock invocation) throws ServiceException {
				Object[] args = invocation.getArguments();
				Integer arg = (Integer) args[0];
				if (arg == null) {
					throw new ServiceException();
				}
				if (arg == 100) {
					return null;
				}
				return ArtistServiceTest.this.artists.get(arg - 1);
			}
		});
		Mockito.when(dao.findArtistsByFirstnameLastname(anyString())).thenAnswer(new Answer<List<Artist>>() {
			public List<Artist> answer(InvocationOnMock invocation) throws ServiceException {
				Object[] args = invocation.getArguments();
				String arg = (String) args[0];
				List<Artist> as = new ArrayList<Artist>();
				
				if (arg.equals("Robbie".toUpperCase())) {
					as.add(ArtistServiceTest.this.artists.get(0));
				}
				if (arg.equals("Williams".toUpperCase())) {
					as.add(ArtistServiceTest.this.artists.get(0));
					as.add(ArtistServiceTest.this.artists.get(1));
				}
				
				return as;
			}
		});
		
		Mockito.when(dao.findAll()).thenReturn(this.artists);
		
		artistServiceImpl.setArtistDao(dao);
		
	}
	
	@Test(expected=ArtistException.class)
	public void getArtist_throwsExceptionIfIdIsNull() throws ArtistException {
		Artist a = this.artistServiceImpl.getArtist(null);
	}
	
	@Test
	public void getLocation_returnsNullIfNotFound() throws ArtistException {
		Artist a = this.artistServiceImpl.getArtist(100);
		
		assertThat(a, is(nullValue()));
	}
	
	@Test
	public void findArtists_findsArtistsByFirstname() throws ArtistException {
		List<Artist> actual = this.artistServiceImpl.findArtists("Robbie");
		
		Artist expected = this.artists.get(0);
		
		assertThat(actual, hasSize(1));
		assertThat(actual.get(0), is(expected));
	}
	
	@Test
	public void findArtists_findsArtistsByLastname() throws ArtistException {
		List<Artist> actual = this.artistServiceImpl.findArtists("Williams");
		
		Artist expected = this.artists.get(0);
		
		assertThat(actual, hasSize(2));
		assertThat(actual.get(0), is(expected));
	}
	
	@Test
	public void findArtists_doesNotFindArtistByDescription() throws ArtistException {
		List<Artist> actual = this.artistServiceImpl.findArtists("description");
		
		assertThat(actual, is(empty()));
	}
	
}
