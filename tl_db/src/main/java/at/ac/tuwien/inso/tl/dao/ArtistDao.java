package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Artist;

@Repository
public interface ArtistDao extends JpaRepository<Artist, Integer>
{

	@Query("SELECT a FROM Artist a WHERE UPPER(a.firstname) like %:searchTerm% or UPPER(a.lastname) like %:searchTerm%")
	public List<Artist> findArtistsByFirstnameLastname(@Param("searchTerm") String searchTerm);

	@Query("SELECT a " + "FROM Artist a " + "LEFT JOIN FETCH a.participations p "
			+ "LEFT JOIN FETCH p.show " + "WHERE a.id = :id")
	public Artist findByIdWithParticipations(@Param("id") Integer id);
}
