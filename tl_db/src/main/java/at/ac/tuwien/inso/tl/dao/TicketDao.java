package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Ticket;

@Repository
public interface TicketDao extends JpaRepository<Ticket, Integer>
{
	@Query("SELECT t " + "FROM Ticket t " + "LEFT JOIN FETCH t.performance p "
			+ "LEFT JOIN FETCH p.show s")
	public List<Ticket> getAllTickets();
	// TODO aufs letzte Monat beschraenken
}
