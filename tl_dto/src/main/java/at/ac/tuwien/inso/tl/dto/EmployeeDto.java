package at.ac.tuwien.inso.tl.dto;

import java.util.Date;

public class EmployeeDto
{

	private Date lastTimeLoggedIn;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLastTimeLoggedIn()
	{
		return lastTimeLoggedIn;
	}

	public void setLastTimeLoggedIn(Date lastTimeLoggedIn)
	{
		this.lastTimeLoggedIn = lastTimeLoggedIn;
	}
}
