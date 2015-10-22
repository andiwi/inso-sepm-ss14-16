package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Location;

@Repository
public interface LocationDao extends JpaRepository<Location, Integer>
{
	@Query("SELECT l FROM Location l WHERE " + "UPPER(l.title) like %:searchTerm% or "
			+ "UPPER(l.street) like %:searchTerm% or " + "UPPER(l.postcode) like %:searchTerm% or "
			+ "UPPER(l.city) like %:searchTerm% or " + "UPPER(l.country) like %:searchTerm%")
	public List<Location> findLocationLike(@Param("searchTerm") String searchTerm);
}
