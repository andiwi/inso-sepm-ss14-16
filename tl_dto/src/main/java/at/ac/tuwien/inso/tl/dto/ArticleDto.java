package at.ac.tuwien.inso.tl.dto;

import java.util.List;

public class ArticleDto
{
	private Integer id;
	private String title;
	private String description;
	private Integer price;
	private Integer available;
	private List<OrderItemDto> orderItems;
	private ShowDto show;
	private Integer amount;

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

	public Integer getPrice()
	{
		return price;
	}

	public void setPrice(Integer price)
	{
		this.price = price;
	}

	public Integer getAvailable()
	{
		return available;
	}

	public void setAvailable(Integer available)
	{
		this.available = available;
	}

	public List<OrderItemDto> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems)
	{
		this.orderItems = orderItems;
	}

	public ShowDto getShow()
	{
		return show;
	}

	public void setShow(ShowDto show)
	{
		this.show = show;
	}

	public Integer getAmount() {
		return this.amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
