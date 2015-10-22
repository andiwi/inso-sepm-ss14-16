package at.ac.tuwien.inso.tl.datagenerator.generator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.Sex;

@Component
public class CustomerGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(CustomerGenerator.class);

	@Autowired
	private CustomerDao dao;

	public void generate()
	{
		LOG.info("+++++ Generate Customer Data +++++");

		Calendar cal = Calendar.getInstance();

		Customer c = new Customer();
		c.setId(1);
		c.setCustomerNumber("K-000000");
		c.setFirstname("Anonym");
		c.setLastname("");
		cal.set(1900, 1, 1);
		c.setDateOfBirth(cal.getTime());
		c.setSex(Sex.m);
		c.setTelephone("+43 000 000 00 00");
		c.setEmail("anonymus@anonym.com");
		c.setPoints(0);
		this.dao.save(c);

		c = new Customer();
		c.setCustomerNumber("K-420953");
		c.setFirstname("Hans");
		c.setLastname("Huber");
		cal.set(1990, 1, 1);
		c.setDateOfBirth(cal.getTime());
		c.setSex(Sex.m);
		c.setTelephone("+43 001 234 56 78");
		c.setEmail("hans.huber@example.com");
		c.setPoints(30);

		this.dao.save(c);

		c = new Customer();
		c.setCustomerNumber("K-348756");
		c.setFirstname("Max");
		c.setLastname("Maier");
		cal.set(1970, 5, 23);
		c.setDateOfBirth(cal.getTime());
		c.setSex(Sex.m);
		c.setTelephone("+43 000 000 00 01");
		c.setEmail("max.maier@example.com");
		c.setPoints(55);

		this.dao.save(c);
		
		c = new Customer();
		c.setCustomerNumber("K-343456");
		c.setFirstname("Andi");
		c.setLastname("Arbeit");
		cal.set(1991, 1, 18);
		c.setDateOfBirth(cal.getTime());
		c.setSex(Sex.m);
		c.setTelephone("+43 000 000 00 02");
		c.setEmail("andi.arbeit@example.com");
		c.setPoints(110);

		this.dao.save(c);

	}
}
