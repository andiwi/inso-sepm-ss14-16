package at.ac.tuwien.inso.tl.server.security;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hsqldb.rights.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import at.ac.tuwien.inso.tl.dao.EmployeeDao;
import at.ac.tuwien.inso.tl.dto.UserEvent;
import at.ac.tuwien.inso.tl.dto.UserStatusDto;
import at.ac.tuwien.inso.tl.model.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler,
		AuthenticationFailureHandler, LogoutSuccessHandler
{

	private ObjectMapper mapper;

	@Autowired
	EmployeeDao userDao;

	public AuthenticationHandler()
	{
		this.mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException
	{
		UserStatusDto usd = AuthUtil.getUserStatusDto(authentication);
		usd.setEvent(UserEvent.AUTH_SUCCESS);

		this.printUserStatusDto(usd, response);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException
	{
		UserStatusDto usd = new UserStatusDto();
		usd.setEvent(UserEvent.AUTH_FAILURE);

		String[] usernames = request.getParameterValues("user");
		String username = usernames[0];
		List<Employee> users = userDao.findByUsername(username);
		if (!users.isEmpty())
		{
			users.get(0).setFailedLogins(users.get(0).getFailedLogins() + 1);
			this.userDao.save(users.get(0));
		}

		this.printUserStatusDto(usd, response);
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException
	{
		UserStatusDto usd = new UserStatusDto();
		usd.setEvent(UserEvent.LOGOUT);

		this.printUserStatusDto(usd, response);
	}

	private void printUserStatusDto(UserStatusDto usd, HttpServletResponse response)
			throws IOException, ServletException
	{
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);

		String result = null;
		try
		{
			result = this.mapper.writeValueAsString(usd);
		} catch (JsonProcessingException e)
		{
			throw new ServletException(e);
		}

		response.getOutputStream().print(result);
	}

}
