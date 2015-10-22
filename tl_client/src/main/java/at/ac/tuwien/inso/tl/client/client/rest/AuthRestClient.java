package at.ac.tuwien.inso.tl.client.client.rest;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import at.ac.tuwien.inso.tl.client.client.AuthService;
import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.client.exception.ValidationException;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.EmployeeDto;
import at.ac.tuwien.inso.tl.dto.MessageDto;
import at.ac.tuwien.inso.tl.dto.UserEvent;
import at.ac.tuwien.inso.tl.dto.UserStatusDto;

@Component
public class AuthRestClient implements AuthService
{

	private static final Logger LOG = Logger.getLogger(AuthRestClient.class);

	@Autowired
	private RestClient restClient;

	private UserStatusDto user = new UserStatusDto();

	public UserStatusDto getUserStatus()
	{
		return this.user;
	}

	@Override
	public boolean login(String username, String password) throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/login");
		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("user", username);
		form.add("password", password);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(
				form, headers);

		LOG.info("Login " + username + " at " + url);

		UserStatusDto status = null;
		try
		{
			status = restTemplate.postForObject(url, entity, UserStatusDto.class);
		} catch (RestClientException e)
		{
			throw new ServiceException("Login failed: " + e.getMessage(), e);
		}

		if (status.getEvent() == UserEvent.AUTH_FAILURE)
		{
			return false;
		}

		LOG.debug("Login " + username + " successful");

		this.user = status;

		return true;
	}

	@Override
	public void logout() throws ServiceException
	{
		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/logout");

		LOG.info("Logout " + this.user.getUsername() + " at " + url);

		UserStatusDto status = null;
		try
		{
			this.updateLastLoginDate(this.user.getId());
			status = restTemplate.getForObject(url, UserStatusDto.class);
		} catch (RestClientException e)
		{
			throw new ServiceException("Logout failed: " + e.getMessage(), e);
		}

		if (status.getEvent() != UserEvent.LOGOUT)
		{
			throw new ServiceException("Logout failed: Invalid event");
		} else
		{
			LOG.debug("Logout " + this.user.getUsername() + " successful");
		}

		this.user = new UserStatusDto();
	}
	
	private void updateLastLoginDate(Integer id) throws ServiceException
	{

		RestTemplate restTemplate = this.restClient.getRestTemplate();
		String url = this.restClient.createServiceUrl("/auth/updatelogindate/" + id);

		HttpHeaders headers = this.restClient.getHttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity<EmployeeDto> entity = new HttpEntity<EmployeeDto>(new EmployeeDto(), headers);

		MessageDto msg = null;
		try
		{
			msg = restTemplate.postForObject(url, entity, MessageDto.class);
		} catch (HttpStatusCodeException e)
		{
			MessageDto errorMsg = this.restClient.mapExceptionToMessage(e);

			if (errorMsg.hasFieldErrors())
			{
				throw new ValidationException(errorMsg.getFieldErrors());
			} else
			{
				throw new ServiceException(errorMsg.getText());
			}
		} catch (RestClientException e)
		{
			throw new ServiceException("Could not publish news: " + e.getMessage(), e);
		}
	}

}
