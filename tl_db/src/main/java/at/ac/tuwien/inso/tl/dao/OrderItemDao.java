package at.ac.tuwien.inso.tl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import at.ac.tuwien.inso.tl.model.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer>
{
}
