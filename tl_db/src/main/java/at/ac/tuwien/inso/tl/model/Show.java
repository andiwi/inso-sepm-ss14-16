package at.ac.tuwien.inso.tl.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Show implements Serializable
{

	private static final long serialVersionUID = 2398987661302928431L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String title;

	@Column(length = 4096)
	private String description;

	@Enumerated(value = EnumType.STRING)
	private ShowType showType;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "show")
	private List<Performance> performances;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "show")
	private List<Participation> participations;

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

	public ShowType getShowType()
	{
		return showType;
	}

	public void setShowType(ShowType showType)
	{
		this.showType = showType;
	}

	public List<Performance> getPerformances()
	{
		return performances;
	}

	public void setPerformances(List<Performance> performances)
	{
		this.performances = performances;

	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((participations == null) ? 0 : participations.hashCode());
		result = prime * result + ((performances == null) ? 0 : performances.hashCode());
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
		Show other = (Show) obj;
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
		if (participations == null)
		{
			if (other.participations != null)
				return false;
		} else if (!participations.equals(other.participations))
			return false;
		if (performances == null)
		{
			if (other.performances != null)
				return false;
		} else if (!performances.equals(other.performances))
			return false;
		if (showType != other.showType)
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
