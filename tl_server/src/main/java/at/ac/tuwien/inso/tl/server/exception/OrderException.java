package at.ac.tuwien.inso.tl.server.exception;

public class OrderException extends Exception
{
	private static final long serialVersionUID = -8021209564575154313L;

	public OrderException()
	{
		super();
	}

	public OrderException(String message)
	{
		super(message);
	}

	public OrderException(Throwable throwable)
	{
		super(throwable);
	}
}
