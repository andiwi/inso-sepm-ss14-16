package at.ac.tuwien.inso.tl.datagenerator.generator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.dao.OrderDao;
import at.ac.tuwien.inso.tl.dao.OrderItemDao;
import at.ac.tuwien.inso.tl.dao.TicketDao;
import at.ac.tuwien.inso.tl.model.Order;
import at.ac.tuwien.inso.tl.model.OrderItem;

@Component
public class OrderGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(OrderGenerator.class);

	@Autowired
	OrderDao dao;
	@Autowired
	OrderItemDao idao;
	@Autowired
	CustomerDao cdao;
	@Autowired
	TicketDao tdao;

	public void generate()
	{
		LOG.info("+++++ Generate Order Data +++++");
		
		Order o = new Order();
		o.setId(1);
		o.setOrderNumber("O-167234");
		o.setCustomer(cdao.findOne(1));
		Calendar c = Calendar.getInstance();
		c.set(2014, 02, 13, 15, 43, 22);
		o.setOrderedAt(c.getTime());
		this.dao.save(o);

		OrderItem i = new OrderItem();
		i.setId(1);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(2));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(this.dao.findOne(1));
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(2);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(3));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(this.dao.findOne(1));
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(3);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(4));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(this.dao.findOne(1));
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(4);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(5));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(this.dao.findOne(1));
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(5);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(6));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(this.dao.findOne(1));
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(6);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(7));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(this.dao.findOne(1));
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(7);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(8));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(this.dao.findOne(1));
		this.idao.save(i);
		
		//2
		o = new Order();
		o.setId(2);
		o.setOrderNumber("O-167124");
		o.setCustomer(cdao.findOne(1));
		c = Calendar.getInstance();
		c.set(2014, 02, 13, 15, 43, 22);
		o.setOrderedAt(c.getTime());
		this.dao.save(o);

		i = new OrderItem();
		i.setId(8);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(109));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(9);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(110));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(10);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(111));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(11);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(112));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(12);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(113));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(13);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(114));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		//3
				o = new Order();
				o.setId(3);
				o.setOrderNumber("O-167235");
				o.setCustomer(cdao.findOne(1));
				c = Calendar.getInstance();
				c.set(2014, 02, 13, 15, 43, 22);
				o.setOrderedAt(c.getTime());
				this.dao.save(o);
		
		i = new OrderItem();
		i.setId(14);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(115));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(15);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(116));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(16);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(117));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(17);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(118));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(18);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(119));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		
		//4
				o = new Order();
				o.setId(4);
				o.setOrderNumber("O-267234");
				o.setCustomer(cdao.findOne(1));
				c = Calendar.getInstance();
				c.set(2014, 02, 13, 15, 43, 22);
				o.setOrderedAt(c.getTime());
				this.dao.save(o);
		i = new OrderItem();
		i.setId(19);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(120));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(20);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(121));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(21);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(122));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(22);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(123));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(23);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(124));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		//5
				o = new Order();
				o.setId(5);
				o.setOrderNumber("O-123234");
				o.setCustomer(cdao.findOne(1));
				c = Calendar.getInstance();
				c.set(2014, 02, 13, 15, 43, 22);
				o.setOrderedAt(c.getTime());
				this.dao.save(o);
		i = new OrderItem();
		i.setId(24);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(125));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(25);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(126));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(26);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(127));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(27);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(128));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		//6
				o = new Order();
				o.setId(6);
				o.setOrderNumber("O-165234");
				o.setCustomer(cdao.findOne(1));
				c = Calendar.getInstance();
				c.set(2014, 02, 13, 15, 43, 22);
				o.setOrderedAt(c.getTime());
				this.dao.save(o);
		i = new OrderItem();
		i.setId(28);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(129));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(29);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(130));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(30);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(131));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		//7
				o = new Order();
				o.setId(7);
				o.setOrderNumber("O-167274");
				o.setCustomer(cdao.findOne(1));
				c = Calendar.getInstance();
				c.set(2014, 02, 13, 15, 43, 22);
				o.setOrderedAt(c.getTime());
				this.dao.save(o);
		i = new OrderItem();
		i.setId(31);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(132));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(32);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(133));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(33);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(134));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		//8
				o = new Order();
				o.setId(8);
				o.setOrderNumber("O-160234");
				o.setCustomer(cdao.findOne(1));
				c = Calendar.getInstance();
				c.set(2014, 02, 13, 15, 43, 22);
				o.setOrderedAt(c.getTime());
				this.dao.save(o);
		i = new OrderItem();
		i.setId(34);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(135));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		i = new OrderItem();
		i.setId(35);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(136));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
		//9
				o = new Order();
				o.setId(9);
				o.setOrderNumber("O-175234");
				o.setCustomer(cdao.findOne(1));
				c = Calendar.getInstance();
				c.set(2014, 02, 13, 15, 43, 22);
				o.setOrderedAt(c.getTime());
				this.dao.save(o);
		i = new OrderItem();
		i.setId(36);
		i.setAmount(1);
		i.setTicket(this.tdao.findOne(137));
		i.setPriceUnit(i.getTicket().getPrice());
		i.setTitle(i.getTicket().getTicketNumber());
		i.setOrder(o);
		this.idao.save(i);
		
	}
}
