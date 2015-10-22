/**
 * 
 */
package at.ac.tuwien.inso.tl.dto;

/**
 * @author beks
 * 
 */
public class ParticipationDto
{

	private Integer ID;
	private String artistRole;
	private ArtistDto artist;
	private ShowDto show;

	public Integer getID()
	{
		return ID;
	}

	public void setID(Integer iD)
	{
		ID = iD;
	}

	public String getArtistRole()
	{
		return artistRole;
	}

	public void setArtistRole(String artistRole)
	{
		this.artistRole = artistRole;
	}

	public ArtistDto getArtist()
	{
		return artist;
	}

	public void setArtists(ArtistDto artist)
	{
		this.artist = artist;
	}

	public ShowDto getShow()
	{
		return show;
	}

	public void setShows(ShowDto show)
	{
		this.show = show;
	}
}
