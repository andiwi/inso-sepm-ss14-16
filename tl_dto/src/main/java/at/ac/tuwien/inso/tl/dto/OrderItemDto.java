package at.ac.tuwien.inso.tl.dto;

public class OrderItemDto
{
	private Integer id;
	private String title;
	private Integer amount;
	private Integer priceUnit;
	private OrderDto order;
	private ArticleDto article;
	private TicketDto ticket;

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

	public Integer getAmount()
	{
		return amount;
	}

	public void setAmount(Integer amount)
	{
		this.amount = amount;
	}

	public Integer getPriceUnit()
	{
		return priceUnit;
	}

	public void setPriceUnit(Integer priceUnit)
	{
		this.priceUnit = priceUnit;
	}

	public OrderDto getOrder()
	{
		return order;
	}

	public void setOrder(OrderDto order)
	{
		this.order = order;
	}

	public ArticleDto getArticle()
	{
		return article;
	}

	public void setArticle(ArticleDto article)
	{
		this.article = article;
	}

	public TicketDto getTicket()
	{
		return ticket;
	}

	public void setTicket(TicketDto ticket)
	{
		this.ticket = ticket;
	}
}
