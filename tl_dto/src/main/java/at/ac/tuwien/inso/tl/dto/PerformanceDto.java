package at.ac.tuwien.inso.tl.dto;

import java.util.Date;
import java.util.List;

public class PerformanceDto
{
	private Integer id;
	private Date startsAt;
	private Integer duration;
	private Integer availableTickets;
	private ShowDto show;
	private RoomDto room;
	private List<TicketDto> tickets;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Date getStartsAt()
	{
		return startsAt;
	}

	public void setStartsAt(Date startsAt)
	{
		this.startsAt = startsAt;
	}

	public Integer getDuration()
	{
		return duration;
	}

	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}

	public ShowDto getShow()
	{
		return show;
	}

	public void setShow(ShowDto show)
	{
		this.show = show;
	}

	public RoomDto getRoom()
	{
		return room;
	}

	public void setRoom(RoomDto room)
	{
		this.room = room;
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
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startsAt == null) ? 0 : startsAt.hashCode());
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
		PerformanceDto other = (PerformanceDto) obj;
		if (duration == null)
		{
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startsAt == null)
		{
			if (other.startsAt != null)
				return false;
		} else if (!startsAt.equals(other.startsAt))
			return false;
		return true;
	}

	public Integer getAvailableTickets()
	{
		return availableTickets;
	}

	public void setAvailableTickets(Integer availableTickets)
	{
		this.availableTickets = availableTickets;
	}
}