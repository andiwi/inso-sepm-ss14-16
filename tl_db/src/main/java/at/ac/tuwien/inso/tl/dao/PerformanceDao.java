package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.ac.tuwien.inso.tl.model.Performance;

public interface PerformanceDao extends JpaRepository<Performance, Integer>
{
	@Query("SELECT p FROM Performance p " + "JOIN p.room r " + "JOIN r.location l "
			+ "JOIN p.show s " + "WHERE l.id = :locationId")
	public List<Performance> findByLocationId(@Param("locationId") Integer locationId);

	@Query("SELECT p FROM Performance p " + "JOIN FETCH p.room r " + "JOIN FETCH r.location l "
			+ "JOIN FETCH p.show s " + "JOIN FETCH p.tickets t " + "JOIN FETCH t.seat e "
			+ "LEFT JOIN FETCH t.reservation v " + "LEFT JOIN FETCH t.orderItem o "
			+ "WHERE p.id = :id")
	public Performance findById(@Param("id") Integer id);

	@Query("SELECT p FROM Performance p " + "JOIN p.room r " + "JOIN r.location l "
			+ "JOIN p.show s " + "JOIN s.participations c " + "JOIN c.artist a "
			+ "WHERE a.id = :artistId")
	public List<Performance> findByArtistId(@Param("artistId") Integer artistId);

	@Query("SELECT p FROM Performance p " + "JOIN p.room r " + "JOIN r.location l "
			+ "JOIN p.show s " + "WHERE s.id = :showId")
	public List<Performance> findByShowId(@Param("showId") Integer showId);
}