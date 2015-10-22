package at.ac.tuwien.inso.tl.client.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import at.ac.tuwien.inso.tl.client.gui.controller.PerformanceController;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.PerformanceDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;

public class ShoppingBasket
{

	private Map<PerformanceDto, Set<TicketDto>> shoppingBasket;
	private PerformanceController controller;
	private Map<ArticleDto, Integer> merchandiseShoppingBasket;

	public ShoppingBasket(PerformanceController controller)
	{
		this.shoppingBasket = new HashMap<PerformanceDto, Set<TicketDto>>();
		this.merchandiseShoppingBasket = new HashMap<ArticleDto, Integer>();
		this.controller = controller;
	}

	public void addTicket(TicketDto ticket)
	{
		if (!this.shoppingBasket.containsKey(ticket.getPerformance()))
		{
			this.shoppingBasket.put(ticket.getPerformance(), new HashSet<TicketDto>());
		}
		this.shoppingBasket.get(ticket.getPerformance()).add(ticket);
		this.controller.onShoppingBasketChange();
	}

	public boolean containsTicket(TicketDto ticket)
	{
		return this.shoppingBasket.containsKey(ticket.getPerformance())
				&& this.shoppingBasket.get(ticket.getPerformance()).contains(ticket);
	}

	public void removeTicket(TicketDto ticket)
	{
		if (this.shoppingBasket.containsKey(ticket.getPerformance()))
		{
			this.shoppingBasket.get(ticket.getPerformance()).remove(ticket);
			if (this.shoppingBasket.get(ticket.getPerformance()).isEmpty())
			{
				this.shoppingBasket.remove(ticket.getPerformance());
			}
		}
		this.controller.onShoppingBasketChange();
	}

	public void clear()
	{
		this.shoppingBasket.clear();
		this.merchandiseShoppingBasket.clear();
		this.controller.onShoppingBasketChange();
	}

	public void addAll(List<TicketDto> tickets)
	{
		for (TicketDto t : tickets)
		{
			this.addTicket(t);
		}
		this.controller.onShoppingBasketChange();
	}

	public List<TicketDto> getTicketList()
	{
		List<TicketDto> tickets = new ArrayList<TicketDto>();
		for (Map.Entry<PerformanceDto, Set<TicketDto>> entry : this.shoppingBasket.entrySet())
		{
			tickets.addAll(entry.getValue());
		}
		return tickets;
	}

	public List<PerformanceDto> getPerformanceList()
	{
		return new ArrayList<PerformanceDto>(this.shoppingBasket.keySet());
	}

	public Set<Map.Entry<PerformanceDto, Set<TicketDto>>> entrySet()
	{
		return this.shoppingBasket.entrySet();
	}
	
	public Set<Map.Entry<ArticleDto, Integer>> merchandiseEntrySet()
	{
		return this.merchandiseShoppingBasket.entrySet();
	}
	

	public int getTotalPrice()
	{
		int total = 1;
		for (TicketDto t : this.getTicketList())
		{
			total = total + t.getPrice();
		}
		
		for (Map.Entry<ArticleDto, Integer> entry : this.merchandiseShoppingBasket.entrySet())
		{
			ArticleDto a = entry.getKey();
			total = total + (entry.getValue() * a.getPrice());
		}
		
		return total;
	}
	
	public List<ArticleDto> getArticleList()
	{
		List<ArticleDto> articles = new ArrayList<ArticleDto>();
		for (Map.Entry<ArticleDto, Integer> entry : this.merchandiseShoppingBasket.entrySet())
		{	
			entry.getKey().setAmount(entry.getValue());
			articles.add(entry.getKey());
		}
		return articles;
	}

	public void addArticle(ArticleDto article) {
		boolean alreadyInTheList = false;
		for (Map.Entry<ArticleDto, Integer> entry : this.merchandiseShoppingBasket.entrySet())
		{
			if(entry.getKey().getTitle().equals(article.getTitle())) {
				this.merchandiseShoppingBasket.put(article, entry.getValue() + article.getAmount());
				//this.merchandiseShoppingBasket.remove(entry.getKey());
				alreadyInTheList = true;
				
			}
		}
		if (!alreadyInTheList) {
			this.merchandiseShoppingBasket.put(article, article.getAmount());
		}
		
		this.controller.onShoppingBasketChange();
	}
	
	
	public void removeArticle(ArticleDto article) {
		//if (this.merchandiseShoppingBasket.containsKey(article.getAmount())) {
			this.merchandiseShoppingBasket.remove(article);
		//}
		this.controller.onShoppingBasketChange();
	}
	
}
