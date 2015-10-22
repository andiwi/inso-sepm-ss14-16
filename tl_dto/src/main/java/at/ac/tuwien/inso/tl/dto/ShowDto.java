package at.ac.tuwien.inso.tl.dto;

import java.util.List;

public class ShowDto
{
	private Integer id;
	private String title;
	private String description;
	private String showType;
	private List<PerformanceDto> performances;
	private List<ParticipationDto> participants;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getShowType()
	{
		return showType;
	}

	public void setShowType(String showType)
	{
		this.showType = showType;
	}

	public List<PerformanceDto> getPerformances()
	{
		return performances;
	}

	public void setPerformances(List<PerformanceDto> performances)
	{
		this.performances = performances;
	}

	public List<ParticipationDto> getParticipants()
	{
		return participants;
	}

	public void setParticipants(List<ParticipationDto> participants)
	{
		this.participants = participants;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((showType == null) ? 0 : showType.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ShowDto other = (ShowDto) obj;
		if (description == null)
		{
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null)
		{
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (showType == null)
		{
			if (other.showType != null)
				return false;
		} else if (!showType.equals(other.showType))
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
