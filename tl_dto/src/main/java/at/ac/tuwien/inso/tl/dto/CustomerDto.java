package at.ac.tuwien.inso.tl.dto;

import java.util.Date;
import java.util.List;

public class CustomerDto
{
	private Integer id;

	private String customerNumber;

	private String firstname;
	private String lastname;

	private Sex sex;
	private Date dateOfBirth;

	private String email;
	private String telephone;

	private List<ReservationDto> reservations;
	private List<OrderDto> orders;

	private int points;

	public int getPoints()
	{
		return points;
	}

	public void setPoints(int points)
	{
		this.points = points;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getCustomerNumber()
	{
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber)
	{
		this.customerNumber = customerNumber;
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

	public Sex getSex()
	{
		return sex;
	}

	public void setSex(Sex sex)
	{
		this.sex = sex;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public List<ReservationDto> getReservations()
	{
		return reservations;
	}

	public void setReservations(List<ReservationDto> reservations)
	{
		this.reservations = reservations;
	}

	public List<OrderDto> getOrders()
	{
		return orders;
	}

	public void setOrders(List<OrderDto> orders)
	{
		this.orders = orders;
	}
}
