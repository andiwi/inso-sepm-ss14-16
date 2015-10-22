package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer>
{

	@Query("SELECT c FROM Customer c WHERE ("
			+ "UPPER(c.firstname) like %:searchTerm% or "
			+ "UPPER(c.lastname) like %:searchTerm% or "
			+ "LOCATE(UPPER(CONCAT(c.firstname, ' ', c.lastname)), :searchTerm) > 0 or "
			+ "UPPER(c.customerNumber) like %:searchTerm%) and "
			+ "id != 1")
	public List<Customer> findCustomersByNumberFirstnameLastname(
			@Param("searchTerm") String searchTerm);
	
	@Query("SELECT c FROM Customer c WHERE id != 1")
	public List<Customer> findAllWithoutAnonymous();

	@Query("SELECT c FROM Customer c "
			+ "LEFT JOIN FETCH c.reservations r "
			+ "LEFT JOIN FETCH c.orders o "
			+ "WHERE c.id = :id")
	public Customer findOneWithReservationsAndOrders(@Param("id") Integer id);
}
