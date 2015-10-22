package at.ac.tuwien.inso.tl.dto;

import java.util.List;

public class ReservationDto
{
	private Integer id;
	private CustomerDto customer;
	private String reservationNumber;
	private List<TicketDto> tickets;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public CustomerDto getCustomer()
	{
		return customer;
	}

	public void setCustomer(CustomerDto customer)
	{
		this.customer = customer;
	}

	public String getReservationNumber()
	{
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber)
	{
		this.reservationNumber = reservationNumber;
	}

	public List<TicketDto> getTickets()
	{
		return tickets;
	}

	public void setTickets(List<TicketDto> tickets)
	{
		this.tickets = tickets;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reservationNumber == null) ? 0 : reservationNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationDto other = (ReservationDto) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reservationNumber == null)
		{
			if (other.reservationNumber != null)
				return false;
		} else if (!reservationNumber.equals(other.reservationNumber))
			return false;
		return true;
	}
}
