package at.ac.tuwien.inso.tl.server.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import at.ac.tuwien.inso.tl.dao.EmployeeDao;
import at.ac.tuwien.inso.tl.model.Employee;

public class UserAuthentication implements UserDetailsService
{
	private static final Logger LOG = Logger.getLogger(UserAuthentication.class);

	@Autowired
	EmployeeDao userDao;

	public UserAuthentication()
	{
	}

	public UserAuthentication(EmployeeDao userDao)
	{
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,
			DataAccessException
	{
		LOG.info("UserAuthentication#loadUserByUsername");
		LOG.info("Username: " + username);

		List<Employee> users = userDao.findByUsername(username);

		Employee loginData = null;
		if (users.isEmpty())
		{
			throw new UsernameNotFoundException(String.format("user %s does not exists", username));
		}

		if (1 == users.size())
		{
			loginData = users.get(0);
		} else
		{
			throw new UsernameNotFoundException(
					String.format("Username %s is not unique", username));
		}

		if (loginData != null && loginData.getFailedLogins() > 5)
		{
			throw new UsernameNotFoundException(String.format("User %s has too many failed logins",
					username));
		}

		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority("TICKET_MANAGER"));

		TicketlineUser ret = new TicketlineUser(
				loginData.getId(),
				loginData.getUsername(),
				loginData.getPassword(),
				auths,
				loginData.getFirstname(),
				loginData.getLastname(),
				new Date(loginData.getLastTimeLoggedIn().getTime()));
		
		return ret;
	}

	public void setUserDao(EmployeeDao userDao)
	{
		this.userDao = userDao;
	}
}
