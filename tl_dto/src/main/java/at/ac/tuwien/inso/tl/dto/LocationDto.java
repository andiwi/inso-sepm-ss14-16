package at.ac.tuwien.inso.tl.dto;

import java.util.List;

public class LocationDto
{
	private Integer id;
	private String title;
	private String description;
	private String street;
	private String postcode;
	private String city;
	private String country;
	private List<PerformanceDto> performances;

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

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getPostcode()
	{
		return postcode;
	}

	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public List<PerformanceDto> getPerformances()
	{
		return performances;
	}

	public void setPerformances(List<PerformanceDto> performances)
	{
		this.performances = performances;
	}
}
