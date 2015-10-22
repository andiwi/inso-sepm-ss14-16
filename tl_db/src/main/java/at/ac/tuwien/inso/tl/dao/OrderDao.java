package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer>
{

	@Query("SELECT o FROM Order o " + "LEFT JOIN FETCH o.customer c " + "WHERE "
			+ "UPPER(c.firstname) like %:searchTerm% or "
			+ "UPPER(c.lastname) like %:searchTerm% or "
			+ "UPPER(c.customerNumber) like %:searchTerm%")
	public List<Order> findOrderLike(@Param("searchTerm") String upperCase);

	@Query("SELECT o " +
		   "FROM Order o " +
		   "LEFT JOIN FETCH o.orderItems i " +
		   "LEFT JOIN FETCH i.article a " +
		   "LEFT JOIN FETCH o.customer c " +
		   "LEFT JOIN FETCH i.ticket t " +
		   "LEFT JOIN FETCH t.performance p " +
		   "LEFT JOIN FETCH p.show s " +
		   "WHERE o.id = :id")
	public Order findByIdWithRelatedEntities(@Param("id") Integer id);
	
}
