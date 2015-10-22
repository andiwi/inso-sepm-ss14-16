package at.ac.tuwien.inso.tl.dto;

import java.util.List;

public class ArtistDto
{
	private String firstname;
	private String lastname;
	private String description;
	private List<ParticipationDto> participations;
	private Integer id;

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<ParticipationDto> getParticipations()
	{
		return participations;
	}

	public void setParticipations(List<ParticipationDto> participations)
	{
		this.participations = participations;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
}
