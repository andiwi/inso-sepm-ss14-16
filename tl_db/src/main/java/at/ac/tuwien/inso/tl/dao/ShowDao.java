package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.model.Show;

@Repository
public interface ShowDao extends JpaRepository<Show, Integer>
{
	@Query("SELECT s " + "FROM Show s " + "WHERE UPPER(s.title) like %:searchTerm% "
			+ "OR UPPER(s.showType) like %:searchTerm% "
			+ "OR UPPER(s.description) like %:searchTerm%")
	public List<Show> findShowsByTitleShowTypeOrDescription(@Param("searchTerm") String searchTerm);

	@Query("SELECT s " + "FROM Show s " + "LEFT JOIN FETCH s.performances " + "WHERE s.id = :id")
	public Show findByIdWithPerformances(@Param("id") Integer id);

	@Query("SELECT s FROM Show s, Participation p WHERE p.artist.id = :id and p.show.id = s.id")
	public List<Show> findShowsByArtistId(@Param("id") Integer id);
}
