package at.ac.tuwien.inso.tl.server.service;

import java.util.List;

import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;

public interface CustomerService
{
	/**
	 * Returns the customer object identified by the given id
	 * 
	 * @param id
	 *            of the customer object
	 * @return the customer object
	 * @throws ServiceException
	 */
	public Customer getCustomer(Integer id) throws ServiceException;

	/**
	 * Saves the given Customer object and returns the saved entity
	 * 
	 * @param customer
	 *            object to persist
	 * @return the saved entity
	 * @throws ServiceException
	 */
	public Customer save(Customer customer) throws ServiceException;

	/**
	 * Returns a collection of all customers whose nr, firstname or lastname
	 * contains the given search string.
	 * 
	 * @param search
	 *            string to look for
	 * @return java.util.List
	 * @throws ServiceException
	 */
	public List<Customer> findCustomers(String search) throws ServiceException;
}
