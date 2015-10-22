package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.Sex;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.CustomerService;

public class CustomerServiceIntegrationTest extends AbstractServiceIntegrationTest
{
	@Autowired
	private CustomerService customerService;
	
	@Before
	public void setUp(){}
	
	@Test(expected=ServiceException.class)
	public void getCustomer_throwsExceptionIfIdIsNull() throws ServiceException {
		this.customerService.getCustomer(null);
	}
	
	@Test
	public void getCustomer_returnsNullIfNotFound() throws ServiceException {
		Customer a = this.customerService.getCustomer(100);
		
		assertThat(a, is(nullValue()));
	}
	
	@Test
	public void getCustomer_returnsCorrectCustomer() throws ServiceException {
		Customer actual = this.customerService.getCustomer(1);
		assertThat(actual, hasProperty("firstname", is("Florian")));
	}
	
	@Test
	public void findCustomers_returnsAllCustomersIfSearchStringIsEmpty() throws ServiceException {
		List<Customer> actual = this.customerService.findCustomers("");

		assertThat(actual, hasSize(10));
	}
	
	@Test
	public void findCustomers_getAllCustomersIfSearchStringIsNull() throws ServiceException {
		List<Customer> actual = this.customerService.findCustomers(null);
		assertThat(actual, hasSize(10));
	}
	
	@Test
	public void findCustomers_findsCustomersByFirstname() throws ServiceException {
		List<Customer> actual = this.customerService.findCustomers("Andreas");
		assertThat(actual, hasSize(1));
	}
	
	@Test
	public void saveCustomer_shouldReturnCustomer() throws ServiceException
	{
		Customer c = new Customer();
		c.setCustomerNumber("A11");
		c.setFirstname("Matthias");
		c.setLastname("Wagner");
		c.setPoints(0);
		c.setSex(Sex.m);
		
		Customer ret = this.customerService.save(c);
		
		assert(c.equals(ret) == true);
	}
}
