package at.ac.tuwien.inso.tl.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Employee;

public class EmployeeDaoTest extends AbstractDaoTest
{
	@Autowired
	private EmployeeDao edao;
	
	@Test
	public void testFindAll(){
		List<Employee> employees = edao.findAll();
		assertEquals("Check DB initial data - is one first", 1, employees.size()); 
	}
}
