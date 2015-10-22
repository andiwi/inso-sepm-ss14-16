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

@Entity
public class Participation implements Serializable
{
	private static final long serialVersionUID = 3071965473717124011L;

	@Id
	@Column(nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 50)
	private String artistRole;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "show_id", nullable = false)
	private Show show;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getArtistRole()
	{
		return artistRole;
	}

	public void setArtistRole(String artistRole)
	{
		this.artistRole = artistRole;
	}

	public Show getShow()
	{
		return show;
	}

	public void setShow(Show show)
	{
		this.show = show;
	}

	public Artist getArtist()
	{
		return artist;
	}

	public void setArtist(Artist artist)
	{
		this.artist = artist;
	}
}
