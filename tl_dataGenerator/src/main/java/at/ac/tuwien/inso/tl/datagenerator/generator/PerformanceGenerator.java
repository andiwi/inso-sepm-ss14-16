package at.ac.tuwien.inso.tl.datagenerator.generator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.PerformanceDao;
import at.ac.tuwien.inso.tl.dao.RoomDao;
import at.ac.tuwien.inso.tl.dao.ShowDao;
import at.ac.tuwien.inso.tl.model.Performance;

@Component
public class PerformanceGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(PerformanceGenerator.class);

	@Autowired
	PerformanceDao dao;

	@Autowired
	RoomDao rdao;

	@Autowired
	ShowDao sdao;

	public void generate()
	{
		LOG.info("+++++ Generate Performance Data +++++");

		Calendar cal = Calendar.getInstance();

		// Show1
		Performance p = new Performance();
		p.setId(1);
		cal.set(2014, 10, 21, 19, 30);
		p.setStartsAt(cal.getTime());
		p.setDuration(120);
		p.setRoom(rdao.findOne(1));
		p.setShow(sdao.findOne(1));
		this.dao.save(p);

		p = new Performance();
		p.setId(2);
		cal.set(2014, 6, 13, 20, 0);
		p.setStartsAt(cal.getTime());
		p.setDuration(105);
		p.setRoom(rdao.findOne(1));
		p.setShow(sdao.findOne(1));
		this.dao.save(p);

		p = new Performance();
		p.setId(3);
		cal.set(2014, 10, 10, 19, 00);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(1));
		this.dao.save(p);

		p = new Performance();
		p.setId(4);
		cal.set(2014, 8, 2, 22, 0);
		p.setStartsAt(cal.getTime());
		p.setDuration(300);
		p.setRoom(rdao.findOne(3));
		p.setShow(sdao.findOne(1));
		this.dao.save(p);

		p = new Performance();
		p.setId(5);
		cal.set(2014, 5, 30, 22, 0);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(4));
		p.setShow(sdao.findOne(1));
		this.dao.save(p);

		p = new Performance();
		p.setId(6);
		cal.set(2014, 11, 2, 23, 30);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(4));
		p.setShow(sdao.findOne(1));
		this.dao.save(p);

		// Show 2
		p = new Performance();
		p.setId(7);
		cal.set(2014, 11, 4, 20, 15);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(4));
		p.setShow(sdao.findOne(2));
		this.dao.save(p);

		// MOVIES
		p = new Performance();
		p.setId(8);
		cal.set(2014, 9, 14, 21, 10);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(6));
		this.dao.save(p);

		p = new Performance();
		p.setId(9);
		cal.set(2014, 9, 13, 20, 15);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(6));
		this.dao.save(p);
		
		p = new Performance();
		p.setId(10);
		cal.set(2014, 9, 10, 21, 10);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(7));
		this.dao.save(p);

		p = new Performance();
		p.setId(11);
		cal.set(2014, 9, 11, 20, 20);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(7));
		this.dao.save(p);
		
		p = new Performance();
		p.setId(12);
		cal.set(2014, 9, 3, 21, 10);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(1));
		p.setShow(sdao.findOne(8));
		this.dao.save(p);

		p = new Performance();
		p.setId(13);
		cal.set(2014, 9, 20, 19, 15);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(9));
		this.dao.save(p);
		
		p = new Performance();
		p.setId(14);
		cal.set(2014, 9, 16, 22, 10);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(10));
		this.dao.save(p);

		p = new Performance();
		p.setId(15);
		cal.set(2014, 7, 8, 20, 15);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(11));
		this.dao.save(p);
		
		p = new Performance();
		p.setId(16);
		cal.set(2014, 9, 5, 21, 10);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(12));
		this.dao.save(p);

		p = new Performance();
		p.setId(17);
		cal.set(2014, 9, 12, 21, 15);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(2));
		p.setShow(sdao.findOne(13));
		this.dao.save(p);
		
		p = new Performance();
		p.setId(18);
		cal.set(2014, 10, 6, 21, 10);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(1));
		p.setShow(sdao.findOne(14));
		this.dao.save(p);

		p = new Performance();
		p.setId(19);
		cal.set(2014, 9, 9, 20, 15);
		p.setStartsAt(cal.getTime());
		p.setDuration(null);
		p.setRoom(rdao.findOne(1));
		p.setShow(sdao.findOne(15));
		this.dao.save(p);
		
	}
}
