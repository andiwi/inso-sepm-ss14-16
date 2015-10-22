package at.ac.tuwien.inso.tl.dto;

public class RoomDto
{
	private Integer id;
	private String title;
	private LocationDto location;

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

	public LocationDto getLocation()
	{
		return location;
	}

	public void setLocation(LocationDto location)
	{
		this.location = location;
	}
}
