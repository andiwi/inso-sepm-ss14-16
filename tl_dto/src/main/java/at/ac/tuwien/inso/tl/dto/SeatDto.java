package at.ac.tuwien.inso.tl.dto;

public class SeatDto
{
	private Integer id;
	private RoomDto room;
	private String seatNumber;
	private Integer row;
	private Integer column;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public RoomDto getRoom()
	{
		return room;
	}

	public void setRoom(RoomDto room)
	{
		this.room = room;
	}

	public String getSeatNumber()
	{
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber)
	{
		this.seatNumber = seatNumber;
	}

	public Integer getRow()
	{
		return row;
	}

	public void setRow(Integer row)
	{
		this.row = row;
	}

	public Integer getColumn()
	{
		return column;
	}

	public void setColumn(Integer column)
	{
		this.column = column;
	}
}