package at.ac.tuwien.inso.tl.server.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.EmployeeDao;
import at.ac.tuwien.inso.tl.model.Employee;
import at.ac.tuwien.inso.tl.server.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService
{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public void updateLoginDate(Integer id)
	{
		Employee e = this.employeeDao.findOne(id);
		e.setLastTimeLoggedIn(new Date());
		this.employeeDao.save(e);
	}

}
