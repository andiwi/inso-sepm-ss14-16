package at.ac.tuwien.inso.tl.server.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.tuwien.inso.tl.dao.ArticleDao;
import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.dao.OrderDao;
import at.ac.tuwien.inso.tl.dao.OrderItemDao;
import at.ac.tuwien.inso.tl.dao.ReservationDao;
import at.ac.tuwien.inso.tl.dao.TicketDao;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.OrderItemDto;
import at.ac.tuwien.inso.tl.dto.ReservationDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;
import at.ac.tuwien.inso.tl.model.Article;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.MethodOfPayment;
import at.ac.tuwien.inso.tl.model.Order;
import at.ac.tuwien.inso.tl.model.OrderItem;
import at.ac.tuwien.inso.tl.model.Reservation;
import at.ac.tuwien.inso.tl.model.Ticket;
import at.ac.tuwien.inso.tl.server.exception.OrderException;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.OrderService;
import at.ac.tuwien.inso.tl.server.service.ReservationService;
import at.ac.tuwien.inso.tl.server.util.DtoToEntity;

@Service
public class OrderServiceImpl implements OrderService
{
	private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private ReservationService reservationService;

	@Override
	public Order save(OrderDto orderDto) throws ServiceException
	{

		if(orderDto.getCustomer() == null ||
				orderDto.getOrderItems() == null ||
				orderDto.getMethodOfPayment() == null){
			throw new ServiceException();
		}
		
		Order o = new Order();
		Customer c = this.customerDao.findOne(orderDto.getCustomer().getId());
		
		o.setCustomer(c);
		
		o.setOrderedAt(new Date());
		
		List<OrderItemDto> liste = orderDto.getOrderItems();
		int totalprice = 0;
		for(OrderItemDto oi : liste)
		{
			if (oi.getTicket() != null)
			{
				totalprice += this.ticketDao.findOne(oi.getTicket().getId()).getPrice();
			}
			
			if (oi.getArticle() != null)
			{
				totalprice += this.articleDao.findArticleById(oi.getArticle().getId()).getPrice() * oi.getAmount();
			}
		}
		
		if (orderDto.getUseBonusPoints() != null && orderDto.getUseBonusPoints())
		{
			if (o.getCustomer().getPoints() >= 100)
			{
				totalprice = (int)(totalprice * 0.75);
				c.setPoints(c.getPoints() - 100);
				c = this.customerDao.saveAndFlush(c);
			}
			else if (o.getCustomer().getPoints() >= 50)
			{
				totalprice = (int)(totalprice * 0.9);
				o.getCustomer().setPoints(o.getCustomer().getPoints() - 50);
				this.customerDao.save(o.getCustomer());
			}
		}else{
			c.setPoints(c.getPoints() + (int)(totalprice / 100));
			c = this.customerDao.saveAndFlush(c);
		}
		
		o.setPriceTotal(totalprice);
		o.setOrderNumber("O-" + String.format("%06d", (new Random()).nextInt(1000000)));
		o.setMethodOfPayment(DtoToEntity.convert(orderDto.getMethodOfPayment()));
		
		Order result;
		try
		{
			result = this.orderDao.save(o);
			for (OrderItemDto oi : liste)
			{
				OrderItem oii = new OrderItem();
				if (oi.getTicket() != null)
				{
					Ticket t = this.ticketDao.findOne(oi.getTicket().getId());
					oii.setTicket(t);
					oii.setOrder(result);
					oii.setPriceUnit(t.getPrice());
					oii.setTitle(t.getTicketNumber());
					oii.setAmount(1);
				}
				if (oi.getArticle() != null)
				{
					Article a = this.articleDao.findArticleById(oi.getArticle().getId());
					oii.setArticle(a);
					oii.setOrder(result);
					oii.setPriceUnit(a.getPrice());
					oii.setTitle(a.getTitle());
					oii.setAmount(oi.getAmount());
				}
				oii = this.orderItemDao.save(oii);
			}
			this.orderItemDao.flush();
			if (orderDto.getReservation() != null) {
				this.reservationService.delete(orderDto.getReservation().getId());
			}
		} catch (Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		return result;
	}

	@Override
	public Order getOrder(Integer id) throws ServiceException
	{
		try
		{
			return this.orderDao.findByIdWithRelatedEntities(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Order> findOrders(String search) throws ServiceException
	{
		try
		{
			if (search == null || search.isEmpty())
			{
				return this.orderDao.findAll();
			} else
			{
				return this.orderDao.findOrderLike(search.toUpperCase());
			}
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(OrderDto dto) throws ServiceException
	{
		try
		{
			Order o = this.orderDao.findByIdWithRelatedEntities(dto.getId());
			
			o.setMethodOfPayment(DtoToEntity.convert(dto.getMethodOfPayment()));
			
			// set new customer (may be the same as before)
			o.setCustomer(this.customerDao.findOne(dto.getCustomer().getId()));

			// update tickets
			List<OrderItem> ois = new ArrayList<OrderItem>();
			List<Integer> ticketIds = new ArrayList<Integer>();
			List<Integer> articleIdsOld = new ArrayList<Integer>();
			List<Integer> articleIdsNew = new ArrayList<Integer>();

			for (OrderItem oi : o.getOrderItems()) {
				if (oi.getArticle() != null){
					articleIdsOld.add(oi.getArticle().getId());
				}
			}
			
			//iterate dto ois and create an oi for each ticket/article
			//that is not already ordered
			for (OrderItemDto oi : dto.getOrderItems()) {
				if(oi.getTicket() != null){
					Ticket t = this.ticketDao.findOne(oi.getTicket().getId());
					if (t.getOrderItem() == null) {
						OrderItem oii = new OrderItem();
						oii.setOrder(o);
						oii.setTicket(t);
						oii.setAmount(1);
						oii.setPriceUnit(t.getPrice());
						oii.setTitle(t.getTicketNumber());
						ois.add(oii);
						this.orderItemDao.saveAndFlush(oii);
						o.getOrderItems().add(oii);
					}
					ticketIds.add(t.getId());
				}
				if(oi.getArticle() != null){
					Article a = this.articleDao.findOne(oi.getArticle().getId());
					if (!articleIdsOld.contains(a.getId())) {
						OrderItem oii = new OrderItem();
						oii.setOrder(o);
						oii.setArticle(a);
						oii.setAmount(oi.getAmount());
						oii.setPriceUnit(a.getPrice());
						oii.setTitle(a.getTitle());
						ois.add(oii);
						this.orderItemDao.saveAndFlush(oii);
						o.getOrderItems().add(oii);
					}
					articleIdsNew.add(a.getId());
				}
			}
			
			//iterate the entity's ois and delete all that have a ticket/article
			//which was not in the dto's ois
			for (OrderItem oi : o.getOrderItems()) {
				if (oi.getTicket() != null && ! ticketIds.contains(oi.getTicket().getId())) {
					this.orderItemDao.delete(oi.getId());
				}
				if (oi.getArticle() != null && ! articleIdsNew.contains(oi.getArticle().getId())) {
					this.orderItemDao.delete(oi.getId());
				}
			}
			
			//save'n'flush
			this.orderItemDao.flush();
			
			o.setOrderItems(null);
			this.orderDao.save(o);
			this.orderDao.flush();
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(Integer id) throws ServiceException
	{
		try
		{
			this.orderDao.delete(id);
		} catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

}
