package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.model.Order;
import at.ac.tuwien.inso.tl.server.exception.OrderException;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface OrderService
{
	/**
	 * Saves the given Order object and returns the saved entity.
	 * Changes bonus points of customer
	 * 
	 * @param Order				The order to persist
	 * @return Order			The saved order
	 * @throws ServiceException
	 */
	public Order save(OrderDto order) throws ServiceException;

	/**
	 * Returns the order object identified by the given id
	 * 
	 * @param id
	 * @return Order
	 * @throws ServiceException
	 */
	public Order getOrder(Integer id) throws ServiceException;

	/**
	 * Returns a collection of all orders that match the given string
	 * (i. e. which are by a customer whose firstname, lastname or customer
	 * number matches the given string)
	 * 
	 * @param search string
	 * @return List<Order>
	 * @throws ServiceException
	 */
	public List<Order> findOrders(String search) throws ServiceException;

	/**
	 * Saves the changes that were made to the order with the id
	 * of the given order.
	 * 
	 * @param Order				The order to update
	 * @return void
	 * @throws ServiceException
	 */
	public void update(OrderDto dto) throws ServiceException;

	/**
	 * Deletes the order with the given id
	 * 
	 * @param id
	 * @return void
	 * @throws ServiceException
	 */
	public void delete(Integer id) throws ServiceException;
}
