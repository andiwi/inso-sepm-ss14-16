package at.ac.tuwien.inso.tl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee implements Serializable
{
	private static final long serialVersionUID = -670177989763664076L;

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Integer failedLogins;

	@Column(nullable = false)
	private Date lastTimeLoggedIn;

	public Date getLastTimeLoggedIn()
	{
		return lastTimeLoggedIn;
	}

	public void setLastTimeLoggedIn(Date lastTimeLoggedIn)
	{
		this.lastTimeLoggedIn = lastTimeLoggedIn;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Integer getFailedLogins()
	{
		return failedLogins;
	}

	public void setFailedLogins(Integer failedLogins)
	{
		this.failedLogins = failedLogins;
	}
}
