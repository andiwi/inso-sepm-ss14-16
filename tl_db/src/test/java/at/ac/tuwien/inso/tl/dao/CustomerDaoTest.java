package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.Sex;

public class CustomerDaoTest extends AbstractDaoTest{
	@Autowired
	private CustomerDao dao;
	
	@Test
	public void testFindAll(){
		List<Customer> customers = dao.findAll();
		assertEquals("Check DB initial data - is ten first", 10, customers.size()); 
	}
	
	@Test
	public void testFindOneById(){
		Customer customer = dao.findOne(1);
		assertEquals(1, customer.getId().longValue());
		assertEquals("Florian", customer.getFirstname());
	}
	
	@Test
	public void testFindOneById_NegativId(){
		assertNull(dao.findOne(-1));
	}
	
	@Test
	public void testFindOneById_InvalidId(){
		assertNull(dao.findOne(0));
	}
	
	@Test
	public void testAddCustomer(){
		assertEquals("Check DB initial data - is ten first", 10, dao.count());
		Customer customer = new Customer();
		customer.setCustomerNumber("C1");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		customer.setDateOfBirth(new Date("01/01/1900"));
		customer.setEmail("email");
		customer.setFirstname("Andreas");
		customer.setLastname("Wittmann");
		customer.setId(11);
		customer.setSex(Sex.m);
		customer.setTelephone("133");
		
				
		Customer saved = dao.save(customer);
		assertEquals("Check Customer count - should be 11", 11, dao.count());
		
		customer= null;
		customer = dao.findOne(saved.getId());
		assertNotNull(customer);
	}
	
	@Test
	public void testAddCustomer_shouldThrowException_LastnameNull(){
		assertEquals("Check DB initial data - is ten first", 10, dao.count());
		Customer customer = new Customer();
				
		try {
			dao.save(customer);
			fail("DataIntegrityViolationException not thrown");
		} catch (DataIntegrityViolationException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteCustomer(){
		assertEquals("Check DB initial data - is ten first", 10, dao.count());
		dao.delete(1);

		assertEquals("Check Customer count - should be nine", 9, dao.count());
	}
	
	@Test
	public void testDeleteCustomer_shouldThrowException_InvalidId(){
		assertEquals("Check DB initial data - is ten first", 10, dao.count());
		try {
			dao.delete(-1);
			fail("EmptyResultDataAccessException not thrown");
		} catch (EmptyResultDataAccessException e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void findCustomersByNumberFirstnameLastname()
	{
		List<Customer> customers = dao.findCustomersByNumberFirstnameLastname("ANDREAS");
		assertEquals("Check Customer firstname count - should be one", 1, customers.size());
		
		customers.clear();
		
		customers = dao.findCustomersByNumberFirstnameLastname("WITTMANN");
		assertEquals("Check Customer lastname count - should be one", 1, customers.size());
		
	}
}
