package at.ac.tuwien.inso.tl.server.exception;

public class ShowException extends Exception
{
	private static final long serialVersionUID = 6570645280662833061L;

	public ShowException()
	{
		super();
	}

	public ShowException(String message)
	{
		super(message);
	}

	public ShowException(Throwable throwable)
	{
		super(throwable);
	}
}
