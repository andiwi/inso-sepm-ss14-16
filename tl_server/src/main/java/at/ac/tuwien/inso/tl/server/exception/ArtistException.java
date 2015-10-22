package at.ac.tuwien.inso.tl.server.exception;

public class ArtistException extends Exception
{
	private static final long serialVersionUID = 6570645280662833061L;

	public ArtistException()
	{
		super();
	}

	public ArtistException(String message)
	{
		super(message);
	}

	public ArtistException(Throwable throwable)
	{
		super(throwable);
	}
}