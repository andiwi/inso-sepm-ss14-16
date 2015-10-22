package at.ac.tuwien.inso.tl.datagenerator.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.CustomerDao;
import at.ac.tuwien.inso.tl.dao.ReservationDao;
import at.ac.tuwien.inso.tl.dao.TicketDao;
import at.ac.tuwien.inso.tl.model.Reservation;
import at.ac.tuwien.inso.tl.model.Ticket;

@Component
public class ReservationGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(ReservationGenerator.class);

	@Autowired
	ReservationDao dao;
	@Autowired
	CustomerDao cdao;
	@Autowired
	TicketDao tdao;

	public void generate()
	{
		LOG.info("+++++ Generate Reservation Data +++++");

		Reservation r = new Reservation();
		r.setId(1);
		r.setCustomer(cdao.findOne(1));
		r.setReservationNumber("R-273443");
		this.dao.save(r);

		Ticket t = tdao.findOne(1);
		t.setReservation(r);
		this.tdao.save(t);

		r = new Reservation();
		r.setId(2);
		r.setCustomer(cdao.findOne(2));
		r.setReservationNumber("R-639881");
		this.dao.save(r);

		t = tdao.findOne(10);
		t.setReservation(r);
		this.tdao.save(t);
		t = tdao.findOne(20);
		t.setReservation(r);
		this.tdao.save(t);
		t = tdao.findOne(90);
		t.setReservation(r);
		this.tdao.save(t);
	}
}
