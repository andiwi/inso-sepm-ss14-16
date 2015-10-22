package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.dao.OrderDao;
import at.ac.tuwien.inso.tl.dao.OrderItemDao;
import at.ac.tuwien.inso.tl.dao.TicketDao;
import at.ac.tuwien.inso.tl.dto.ArticleDto;
import at.ac.tuwien.inso.tl.dto.CustomerDto;
import at.ac.tuwien.inso.tl.dto.MethodOfPayment;
import at.ac.tuwien.inso.tl.dto.OrderDto;
import at.ac.tuwien.inso.tl.dto.OrderItemDto;
import at.ac.tuwien.inso.tl.dto.TicketDto;
import at.ac.tuwien.inso.tl.model.Customer;
import at.ac.tuwien.inso.tl.model.Order;
import at.ac.tuwien.inso.tl.model.OrderItem;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.OrderService;

public class OrderServiceIntegrationTest extends AbstractServiceIntegrationTest
{
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Before
	public void setUp(){}
	
	@Test(expected=ServiceException.class)
	public void save_throwsExceptionIfNoOrderItems() throws ServiceException {
		OrderDto o = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(1);
		o.setCustomer(c);
		o.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		this.orderService.save(o);
	}

	@Test(expected=ServiceException.class)
	public void save_throwsExceptionIfNoCustomer() throws ServiceException {
		OrderDto o = new OrderDto();
		o.setOrderItems(new ArrayList<OrderItemDto>());
		o.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		this.orderService.save(o);
	}

	@Test(expected=ServiceException.class)
	public void save_throwsExceptionIfNoMethodOfPayment() throws ServiceException {
		OrderDto o = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(1);
		o.setCustomer(c);
		o.setOrderItems(new ArrayList<OrderItemDto>());
		this.orderService.save(o);
	}
	
	@Test
	public void save_setsCorrectDate() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(1);
		oDto.setCustomer(c);
		oDto.setOrderItems(new ArrayList<OrderItemDto>());
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		
		Order o = this.orderService.save(oDto);
		//earlier than now
		assertThat(o.getOrderedAt().getTime(), lessThan((new Date()).getTime()));
		//later than ten minutes ago
		assertThat(o.getOrderedAt().getTime(), greaterThan((new Date()).getTime() - 600000));
	}
	
	@Test
	public void save_calculatesCorrectTotalWithoutBonusPoints() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(1);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(1);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		tDto = new TicketDto();
		tDto.setId(2);
		oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		
		Order o = this.orderService.save(oDto);
		
		assertThat(o.getPriceTotal(), is(200));
	}
	
	@Test
	public void save_calculatesCorrectTotalWith50BonusPoints() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(2);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(3);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		tDto = new TicketDto();
		tDto.setId(4);
		oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		oDto.setUseBonusPoints(true);
		
		Order o = this.orderService.save(oDto);
		
		assertThat(o.getPriceTotal(), is((int)(200 * 0.9)));
	}
	
	@Test
	public void save_calculatesCorrectTotalWith100BonusPoints() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(3);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(5);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		tDto = new TicketDto();
		tDto.setId(6);
		oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		oDto.setUseBonusPoints(true);
		
		Order o = this.orderService.save(oDto);
		
		assertThat(o.getPriceTotal(), is((int)(200 * 0.75)));
	}
	
	@Test
	public void save_doesNotReduceBonusPoints() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(1);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(7);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		tDto = new TicketDto();
		tDto.setId(8);
		oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		oDto.setUseBonusPoints(true);
		
		this.orderService.save(oDto);
		
		Customer customer = this.customerDao.findOne(1);
		assertThat(customer.getPoints(), is(38));
	}
	
	@Test
	public void save_addsBonusPoints() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(9);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(16);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		
		this.orderService.save(oDto);
		
		Customer customer = this.customerDao.findOne(9);
		assertThat(customer.getPoints(), is(58));
	}
	
	@Test
	public void save_doesNotAddBonusPointsIfPointsAreUsed() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(9);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(16);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		oDto.setUseBonusPoints(true);
		
		this.orderService.save(oDto);
		
		Customer customer = this.customerDao.findOne(9);
		assertThat(customer.getPoints(), is(8));
	}
	
	@Test
	public void save_reducesCustomersBonusPoints50() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(2);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(9);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		tDto = new TicketDto();
		tDto.setId(10);
		oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		oDto.setUseBonusPoints(true);
		
		this.orderService.save(oDto);
		
		Customer customer = this.customerDao.findOne(2);
		assertThat(customer.getPoints(), is(7));
	}
	
	@Test
	public void save_reducesCustomersBonusPoints100() throws ServiceException {
		OrderDto oDto = new OrderDto();
		CustomerDto c = new CustomerDto();
		c.setId(3);
		oDto.setCustomer(c);
		List<OrderItemDto> oiDtos = new ArrayList<OrderItemDto>();
		
		TicketDto tDto = new TicketDto();
		tDto.setId(11);
		OrderItemDto oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		tDto = new TicketDto();
		tDto.setId(12);
		oiDto = new OrderItemDto();
		oiDto.setTicket(tDto);
		oiDtos.add(oiDto);
		
		oDto.setOrderItems(oiDtos);
		oDto.setMethodOfPayment(MethodOfPayment.BANKACCOUNT);
		oDto.setUseBonusPoints(true);
		
		this.orderService.save(oDto);
		
		Customer customer = this.customerDao.findOne(3);
		assertThat(customer.getPoints(), is(44));
	}
	
	@Test
	public void update_addsAndRemovesTickets() throws ServiceException {
		Order o = this.orderService.getOrder(1);
		
		OrderDto odto = new OrderDto();
		odto.setId(o.getId());
		CustomerDto cdto = new CustomerDto();
		cdto.setId(o.getCustomer().getId());
		odto.setCustomer(cdto);

		assertThat(o.getOrderItems(), hasSize(1));  
		
		List<OrderItemDto> oidtos = new ArrayList<OrderItemDto>();
		for(OrderItem oi : o.getOrderItems()){
			TicketDto tdto = new TicketDto();
			tdto.setId(oi.getTicket().getId());
			OrderItemDto oidto = new OrderItemDto();
			oidto.setTicket(tdto);
			oidtos.add(oidto);
		}
		
		OrderItemDto oidto = new OrderItemDto();
		TicketDto tdto = new TicketDto();
		tdto.setId(13);
		oidto.setTicket(tdto);
		oidtos.add(oidto);

		//add tickets
		odto.setOrderItems(oidtos);
		this.orderService.update(odto);
		o = this.orderService.getOrder(1);
		assertThat(o.getOrderItems(), hasSize(2));

		//remove tickets
		odto.setOrderItems(new ArrayList<OrderItemDto>());
		this.orderService.update(odto);
		o = this.orderService.getOrder(1);
		assertThat(o.getOrderItems(), hasSize(0));
	}

	@Test
	public void update_addsAndRemovesArticles() throws ServiceException {
		Order o = this.orderService.getOrder(3);
		
		OrderDto odto = new OrderDto();
		odto.setId(o.getId());
		CustomerDto cdto = new CustomerDto();
		cdto.setId(o.getCustomer().getId());
		odto.setCustomer(cdto);
		
		assertThat(o.getOrderItems(), hasSize(1));  
		
		List<OrderItemDto> oidtos = new ArrayList<OrderItemDto>();
		for(OrderItem oi : o.getOrderItems()){
			ArticleDto adto = new ArticleDto();
			adto.setId(oi.getArticle().getId());
			OrderItemDto oidto = new OrderItemDto();
			oidto.setArticle(adto);
			oidto.setAmount(1);
			oidtos.add(oidto);
		}
		
		OrderItemDto oidto = new OrderItemDto();
		ArticleDto adto = new ArticleDto();
		adto.setId(3);
		oidto.setArticle(adto);
		oidto.setAmount(1);
		oidtos.add(oidto);

		//add articles
		odto.setOrderItems(oidtos);
		this.orderService.update(odto);
		o = this.orderService.getOrder(3);
		assertThat(o.getOrderItems(), hasSize(2));

		//remove articles
		odto.setOrderItems(new ArrayList<OrderItemDto>());
		this.orderService.update(odto);
		o = this.orderService.getOrder(3);
		assertThat(o.getOrderItems(), hasSize(0));
	}
	
	@Test
	public void delete_deletesOrder() throws ServiceException {
		Order o = this.orderService.getOrder(4);
		assertThat(o, instanceOf(Order.class));
		
		this.orderService.delete(4);
		o = this.orderService.getOrder(4);
		assertThat(o, is(nullValue()));
	}

	@Test
	public void getOrder_returnsNullIfNotExists() throws ServiceException {
		assertThat(this.orderService.getOrder(100), is(nullValue()));
	}

	@Test
	public void getOrder_returnsNullIfIdIsNull() throws ServiceException {
		assertThat(this.orderService.getOrder(null), is(nullValue()));
	}

	@Test
	public void getOrder_returnsShowIfExists() throws ServiceException {
		Order o = this.orderService.getOrder(1); 
		assertThat(o.getId(), is(1));
	}

	@Test
	public void findOrders_returnsAllIfSearchIsNull() throws ServiceException { 
		assertThat(this.orderService.findOrders(null), hasSize(6));
	}

	@Test
	public void findOrders_returnsAllIfSearchIsEmpty() throws ServiceException { 
		assertThat(this.orderService.findOrders(""), hasSize(11));
	}

	@Test
	public void findOrders_searchesInFirstName() throws ServiceException { 
		assertThat(this.orderService.findOrders("est"), hasSize(1));
	}

	@Test
	public void findOrders_searchesInLastName() throws ServiceException { 
		assertThat(this.orderService.findOrders("oo"), hasSize(1));
	}

	@Test
	public void findOrders_searchesInFirstNameCaseInsensitive() throws ServiceException { 
		assertThat(this.orderService.findOrders("EsTi"), hasSize(1));
	}

	@Test
	public void findOrders_searchesInLastNameCaseInsensitive() throws ServiceException { 
		assertThat(this.orderService.findOrders("WiTTMa"), hasSize(3));
	} 

	@Test
	public void findOrders_searchesInCustomerNumber() throws ServiceException { 
		assertThat(this.orderService.findOrders("c"), hasSize(1));
	} 
	
}
