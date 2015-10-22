package at.ac.tuwien.inso.tl.server.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class TicketlineUser implements UserDetails
{

	private static final long serialVersionUID = -8343481930819178599L;

	private String username;

	private String password;

	private String firstName;

	private String lastName;
	
	private Date lastTimeLoggedIn;
	
	private Integer id;

	private List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

	public TicketlineUser(Integer id, String username, String password, List<GrantedAuthority> roles,
			String firstName, String lastName, Date lastTimeLoggedIn)
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastTimeLoggedIn = lastTimeLoggedIn;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.roles;
	}

	@Override
	public String getUsername()
	{
		return this.username;
	}

	@Override
	public String getPassword()
	{
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}
	
	public Date getLastTimeLoggedIn()
	{
		return this.lastTimeLoggedIn;
	}

	public Integer getId()
	{
		return this.id;
	}

}
