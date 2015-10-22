package at.ac.tuwien.inso.tl.datagenerator.generator;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.EmployeeDao;
import at.ac.tuwien.inso.tl.model.Employee;

@Component
public class EmployeeGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(EmployeeGenerator.class);

	@Autowired
	EmployeeDao dao;

	@Autowired
	private PasswordEncoder encoder;

	public void generate()
	{
		LOG.info("+++++ Generate Employee Data +++++");

		Calendar cal = Calendar.getInstance();

		cal.set(2014, 5, 15);
		
		
		Employee e = new Employee();
		e.setLastTimeLoggedIn(new Date(1402576421));
		e.setFirstname("Marvin");
		e.setLastname("Jones");
		e.setUsername("marvin");
		e.setFailedLogins(0);
		e.setPassword(this.encoder.encode("42"));

		this.dao.save(e);
		
		e = new Employee();
		e.setLastTimeLoggedIn(cal.getTime());
		e.setFirstname("Julio");
		e.setLastname("Jones");
		e.setUsername("julio");
		e.setFailedLogins(0);
		e.setPassword(this.encoder.encode("42"));

		this.dao.save(e);
		
	}
}
