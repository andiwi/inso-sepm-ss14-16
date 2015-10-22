package at.ac.tuwien.inso.tl.dto;

public class TicketDto
{
	private Integer id;
	private SeatDto seat;
	private PerformanceDto performance;
	private ReservationDto reservation;
	private OrderItemDto orderItem;
	private Integer price;
	private String category;
	private String ticketNumber;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public SeatDto getSeat()
	{
		return seat;
	}

	public void setSeat(SeatDto seat)
	{
		this.seat = seat;
	}

	public PerformanceDto getPerformance()
	{
		return performance;
	}

	public void setPerformance(PerformanceDto performance)
	{
		this.performance = performance;
	}

	public ReservationDto getReservation()
	{
		return reservation;
	}

	public void setReservation(ReservationDto reservation)
	{
		this.reservation = reservation;
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

	public String getTicketNumber()
	{
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber)
	{
		this.ticketNumber = ticketNumber;
	}

	public OrderItemDto getOrderItem()
	{
		return orderItem;
	}

	public void setOrderItem(OrderItemDto orderItem)
	{
		this.orderItem = orderItem;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		TicketDto other = (TicketDto) obj;
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
		if (price == null)
		{
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (ticketNumber == null)
		{
			if (other.ticketNumber != null)
				return false;
		} else if (!ticketNumber.equals(other.ticketNumber))
			return false;
		return true;
	}
}