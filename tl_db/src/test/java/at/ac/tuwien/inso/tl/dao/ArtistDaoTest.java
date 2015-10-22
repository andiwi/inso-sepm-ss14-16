package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import at.ac.tuwien.inso.tl.model.Artist;

public class ArtistDaoTest extends AbstractDaoTest
{
	@Autowired
	private ArtistDao rdao;
	
	@Test
	public void testFindAll(){
		List<Artist> artist = rdao.findAll();
		assertEquals("Check DB initial data - is ten first", 6, artist.size()); 
	}
	
	@Test
	public void testFindOneById(){
		Artist artist = rdao.findOne(6);
		assertEquals(6, artist.getId().longValue());
		assertEquals("Alicia", artist.getFirstname());
	}
	
	@Test
	public void testFindOneById_NegativId(){
		assertNull(rdao.findOne(-1));
	}
	
	@Test
	public void testFindOneById_InvalidId(){
		assertNull(rdao.findOne(0));
	}
	
	@Test
	public void testAddArtist(){
		assertEquals("Check DB initial data - is six first", 6, rdao.count());
		Artist artist = new Artist();
		artist.setDescription("description");
		artist.setFirstname("Richard");
		artist.setLastname("Lugner");
				
		Artist saved = rdao.save(artist);
		assertEquals("Check Artist count - should be 7", 7, rdao.count());
		
		artist= null;
		artist = rdao.findOne(saved.getId());
		assertNotNull(artist);
	}
	
	@Test
	public void testAddArtist_shouldThrowException_LastnameNull(){
		assertEquals("Check DB initial data - is six first", 6, rdao.count());
		Artist artist = new Artist();
		artist.setDescription("description");
		artist.setFirstname("Richard");
		
		try {
			rdao.save(artist);
			fail("DataIntegrityViolationException not thrown");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteArtist(){
		assertEquals("Check DB initial data - is six first", 6, rdao.count());
		rdao.delete(1);

		assertEquals("Check Artist count - should be five", 5, rdao.count());
	}
	
	@Test
	public void testDeleteArtist_shouldThrowException_InvalidId(){
		assertEquals("Check DB initial data - is six first", 6, rdao.count());
		try {
			rdao.delete(-1);
			fail("EmptyResultDataAccessException not thrown");
		} catch (EmptyResultDataAccessException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void findArtistsByFirstnameLastname()
	{
		List<Artist> artistListFirstname = rdao.findArtistsByFirstnameLastname("ALICIA");
		assertEquals("Check artistListFirstname count - should be one", 1, artistListFirstname.size());
		
		List<Artist> artistListLastname = rdao.findArtistsByFirstnameLastname("KEYS");
		assertEquals("Check artistListLastname count - should be one", 1, artistListLastname.size());
	}
	
	@Test
	public void findByIdWithParticipations()
	{
		Artist artist = rdao.findByIdWithParticipations(1);
		assertEquals("Check participations count - should be one", 1, artist.getParticipations().size());
	}
}
