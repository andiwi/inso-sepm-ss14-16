package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Artist;
import at.ac.tuwien.inso.tl.server.exception.ArtistException;
import at.ac.tuwien.inso.tl.server.service.ArtistService;

public class ArtistServiceIntegrationTest extends AbstractServiceIntegrationTest
{
	@Autowired
	private ArtistService artistService;
	
	@Before
	public void setUp(){}
	
	@Test(expected=ArtistException.class)
	public void getArtist_throwsExceptionIfIdIsNull() throws ArtistException {
		this.artistService.getArtist(null);
	}
	
	@Test
	public void getArtist_returnsNullIfNotFound() throws ArtistException {
		Artist a = this.artistService.getArtist(100);
		
		assertThat(a, is(nullValue()));
	}
	
	@Test
	public void getArtist_returnsCorrectArtist() throws ArtistException {
		Artist actual = this.artistService.getArtist(1);
		assertThat(actual, hasProperty("firstname", is("Robbie")));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void findArtists_returnsAllArtistsIfSearchStringIsEmpty() throws ArtistException {
		List<Artist> actual = this.artistService.findArtists("");

		assertThat(actual, hasSize(6));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void findArtists_returnsAllArtistsIfSearchStringIsNull() throws ArtistException {
		List<Artist> actual = this.artistService.findArtists(null);
		
		assertThat(actual, hasSize(6));
	}
	
	@Test
	public void findArtists_findsArtistsByFirstname() throws ArtistException {
		List<Artist> actual = this.artistService.findArtists("Robbie");
		
		assertThat(actual, hasSize(5));
	}
}
