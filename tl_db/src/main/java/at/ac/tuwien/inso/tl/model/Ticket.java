package at.ac.tuwien.inso.tl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Ticket implements Serializable
{
	private static final long serialVersionUID = 2355163364458707580L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String ticketNumber;

	@Column(nullable = false)
	private Integer price;

	@Column
	private String category;

	@ManyToOne
	@JoinColumn(name = "performance_id", nullable = false)
	private Performance performance;

	@ManyToOne
	@JoinColumn(name = "seat_id", nullable = false)
	private Seat seat;

	@ManyToOne
	@JoinColumn(name = "reservation_id", nullable = true)
	private Reservation reservation;

	@OneToOne(mappedBy = "ticket")
	private OrderItem orderItem;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTicketNumber()
	{
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}

	public Integer getPrice()
	{
		return price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public Performance getPerformance()
	{
		return performance;
	}

	public void setPerformance(Performance performance)
	{
		this.performance = performance;
	}

	public Seat getSeat()
	{
		return seat;
	}

	public void setSeat(Seat seat)
	{
		this.seat = seat;
	}

	public Reservation getReservation()
	{
		return reservation;
	}

	/**
	 * Set new reservation. The method keeps relationships consistency: * this
	 * ticket is removed from the previous reservation * this ticket is added to
	 * next reservation
	 * 
	 * @param owner
	 */
	public void setReservation(Reservation reservation)
	{
		// prevent endless loop
		if (this.reservation != null && this.reservation.equals(reservation))
		{
			return;
		}
		// set new reservation
		Reservation oldReservation = null;
		if (this.reservation != null)
			oldReservation = this.reservation;

		this.reservation = reservation;
		// remove from the old reservation
		if (null != oldReservation)
			oldReservation.setTickets(null);

		// set myself into new reservation
		if (null != reservation)
			reservation.addTicket(this);

	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderItem == null) ? 0 : orderItem.hashCode());
		result = prime * result + ((performance == null) ? 0 : performance.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((reservation == null) ? 0 : reservation.hashCode());
		result = prime * result + ((seat == null) ? 0 : seat.hashCode());
		result = prime * result + ((ticketNumber == null) ? 0 : ticketNumber.hashCode());
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
		Ticket other = (Ticket) obj;
		if (category == null)
		{
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderItem == null)
		{
			if (other.orderItem != null)
				return false;
		} else if (!orderItem.equals(other.orderItem))
			return false;
		if (performance == null)
		{
			if (other.performance != null)
				return false;
		} else if (!performance.equals(other.performance))
			return false;
		if (price == null)
		{
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (reservation == null)
		{
			if (other.reservation != null)
				return false;
		} else if (!reservation.equals(other.reservation))
			return false;
		if (seat == null)
		{
			if (other.seat != null)
				return false;
		} else if (!seat.equals(other.seat))
			return false;
		if (ticketNumber == null)
		{
			if (other.ticketNumber != null)
				return false;
		} else if (!ticketNumber.equals(other.ticketNumber))
			return false;
		return true;
	}

	public OrderItem getOrderItem()
	{
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem)
	{
		this.orderItem = orderItem;
	}
}
