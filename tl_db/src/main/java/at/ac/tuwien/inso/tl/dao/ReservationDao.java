package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Reservation;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer>
{

	@Query("SELECT r FROM Reservation r " + "LEFT JOIN FETCH r.customer c " + "WHERE "
			+ "UPPER(r.reservationNumber) like %:searchTerm% or "
			+ "UPPER(c.firstname) like %:searchTerm% or "
			+ "UPPER(c.lastname) like %:searchTerm% or "
			+ "UPPER(c.customerNumber) like %:searchTerm%")
	public List<Reservation> findReservationLike(@Param("searchTerm") String searchTerm);

	@Query("SELECT r " + "FROM Reservation r " + "LEFT JOIN FETCH r.tickets t "
			+ "LEFT JOIN FETCH r.customer c " + "LEFT JOIN FETCH t.performance p "
			+ "LEFT JOIN FETCH p.show s " + "WHERE r.id = :id")
	public Reservation findByIdWithRelatedEntities(@Param("id") Integer id);

}
