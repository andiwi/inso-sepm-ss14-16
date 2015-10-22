package at.ac.tuwien.inso.tl.server.service.impl;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer getCustomer(Integer id) throws ServiceException
	{
		if (id == null)
		{
			throw new ServiceException();
		}
		
		try
		{
			return customerDao.findOneWithReservationsAndOrders(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public Customer save(Customer customer) throws ServiceException
	{
		try
		{
			if (customer.getCustomerNumber() == null || customer.getCustomerNumber().isEmpty())
			{
				customer.setCustomerNumber("K-"
						+ String.format("%06d", (new Random()).nextInt(1000000)));
			}
			return customerDao.save(customer);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Customer> findCustomers(String search) throws ServiceException
	{
		try
		{
			if (search == null || search.isEmpty())
			{
				return customerDao.findAllWithoutAnonymous();
			} else
			{
				return customerDao.findCustomersByNumberFirstnameLastname(search.toUpperCase());
			}
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

}
