package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.CustomerDto;

public interface CustomerService
{

	public List<CustomerDto> findCustomers(String search) throws ServiceException;

	public CustomerDto getCustomerById(CustomerDto customer) throws ServiceException;

	public CustomerDto createCustomer(CustomerDto customer) throws ServiceException;

	public CustomerDto updateCustomer(CustomerDto customer) throws ServiceException;

}
