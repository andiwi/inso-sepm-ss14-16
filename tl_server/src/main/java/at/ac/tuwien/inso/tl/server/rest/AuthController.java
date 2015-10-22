package at.ac.tuwien.inso.tl.server.rest;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.inso.tl.dto.EmployeeDto;
import at.ac.tuwien.inso.tl.dto.UserStatusDto;
import at.ac.tuwien.inso.tl.model.Employee;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.security.AuthUtil;
import at.ac.tuwien.inso.tl.server.service.EmployeeService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController
{
	private static final Logger LOG = Logger.getLogger(AuthController.class);
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public UserStatusDto getUserStatus() throws ServiceException
	{
		LOG.info("getUserStatus called()");

		return AuthUtil.getUserStatusDto(SecurityContextHolder.getContext().getAuthentication());
	}
	@RequestMapping(value = "/updatelogindate/{id}", method = RequestMethod.POST)
	public void setLogoutDate(@PathVariable("id") Integer id, @Valid @RequestBody EmployeeDto employee) throws ServiceException
	{
		this.employeeService.updateLoginDate(id);
	}

}
