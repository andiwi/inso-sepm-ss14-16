package at.ac.tuwien.inso.tl.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservation implements Serializable
{
	private static final long serialVersionUID = 6362706103122264420L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String reservationNumber;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "reservation")
	private List<Ticket> tickets;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getReservationNumber()
	{
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber)
	{
		this.reservationNumber = reservationNumber;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public List<Ticket> getTickets()
	{
		return tickets;
	}

	public void setTickets(List<Ticket> tickets)
	{
		if (tickets == null)
		{
			this.tickets = null;
		} else
		{
			for (Ticket t : tickets)
			{
				this.addTicket(t);
			}
		}

	}

	/**
	 * Add new Tickets to the Reservation. The method keeps relationships
	 * consistency: * this reservation is set as the reservation in ticket
	 */
	public void addTicket(Ticket ticket)
	{
		if (this.tickets == null)
			this.tickets = new ArrayList<Ticket>();

		// prevent endless loops
		if (this.tickets.contains(ticket))
			return;
		// add new ticket
		this.tickets.add(ticket);
		// set myself into the tickets
		ticket.setReservation(this);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reservationNumber == null) ? 0 : reservationNumber.hashCode());
		result = prime * result + ((tickets == null) ? 0 : tickets.hashCode());
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
		Reservation other = (Reservation) obj;
		if (customer == null)
		{
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
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
		if (tickets == null)
		{
			if (other.tickets != null)
				return false;
		} else if (!tickets.equals(other.tickets))
			return false;
		return true;
	}
}
