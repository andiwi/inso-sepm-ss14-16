package at.ac.tuwien.inso.tl.datagenerator.generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.PerformanceDao;
import at.ac.tuwien.inso.tl.dao.SeatDao;
import at.ac.tuwien.inso.tl.dao.TicketDao;
import at.ac.tuwien.inso.tl.model.Performance;
import at.ac.tuwien.inso.tl.model.Ticket;

@Component
public class TicketGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(TicketGenerator.class);

	@Autowired
	TicketDao dao;

	@Autowired
	PerformanceDao pdao;

	@Autowired
	SeatDao sdao;

	public void generate()
	{
		LOG.info("+++++ Generate Ticket Data +++++");

		//
		// PERFORMANCE 1
		//

		Performance p = this.pdao.findOne(1);

		Ticket t = new Ticket();
		t.setId(1);
		t.setPrice(100);
		t.setTicketNumber("T1");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(2);
		t.setPrice(100);
		t.setTicketNumber("T2");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);

		t = new Ticket();
		t.setId(3);
		t.setPrice(100);
		t.setTicketNumber("T3");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);

		t = new Ticket();
		t.setId(4);
		t.setPrice(100);
		t.setTicketNumber("T4");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);

		t = new Ticket();
		t.setId(5);
		t.setPrice(100);
		t.setTicketNumber("T5");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);

		t = new Ticket();
		t.setId(6);
		t.setPrice(100);
		t.setTicketNumber("T6");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);

		t = new Ticket();
		t.setId(7);
		t.setPrice(100);
		t.setTicketNumber("T7");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(7));
		this.dao.save(t);

		t = new Ticket();
		t.setId(8);
		t.setPrice(100);
		t.setTicketNumber("T8");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(8));
		this.dao.save(t);

		t = new Ticket();
		t.setId(9);
		t.setPrice(100);
		t.setTicketNumber("T9");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(9));
		this.dao.save(t);

		t = new Ticket();
		t.setId(10);
		t.setPrice(100);
		t.setTicketNumber("T10");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(10));
		this.dao.save(t);

		t = new Ticket();
		t.setId(11);
		t.setPrice(100);
		t.setTicketNumber("T11");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(11));
		this.dao.save(t);

		t = new Ticket();
		t.setId(12);
		t.setPrice(100);
		t.setTicketNumber("T12");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(13);
		t.setPrice(100);
		t.setTicketNumber("T13");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(13));
		this.dao.save(t);

		t = new Ticket();
		t.setId(14);
		t.setPrice(100);
		t.setTicketNumber("T14");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(14));
		this.dao.save(t);

		t = new Ticket();
		t.setId(15);
		t.setPrice(100);
		t.setTicketNumber("T15");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(15));
		this.dao.save(t);

		t = new Ticket();
		t.setId(16);
		t.setPrice(100);
		t.setTicketNumber("T16");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(16));
		this.dao.save(t);

		t = new Ticket();
		t.setId(17);
		t.setPrice(100);
		t.setTicketNumber("T17");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(17));
		this.dao.save(t);

		t = new Ticket();
		t.setId(18);
		t.setPrice(100);
		t.setTicketNumber("T18");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(18));
		this.dao.save(t);

		t = new Ticket();
		t.setId(19);
		t.setPrice(100);
		t.setTicketNumber("T19");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(19));
		this.dao.save(t);

		t = new Ticket();
		t.setId(20);
		t.setPrice(100);
		t.setTicketNumber("T20");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(20));
		this.dao.save(t);

		t = new Ticket();
		t.setId(21);
		t.setPrice(100);
		t.setTicketNumber("T21");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(21));
		this.dao.save(t);

		t = new Ticket();
		t.setId(22);
		t.setPrice(100);
		t.setTicketNumber("T22");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(22));
		this.dao.save(t);

		t = new Ticket();
		t.setId(23);
		t.setPrice(100);
		t.setTicketNumber("T23");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(23));
		this.dao.save(t);

		t = new Ticket();
		t.setId(24);
		t.setPrice(100);
		t.setTicketNumber("T24");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(24));
		this.dao.save(t);

		t = new Ticket();
		t.setId(25);
		t.setPrice(100);
		t.setTicketNumber("T25");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(25));
		this.dao.save(t);

		t = new Ticket();
		t.setId(26);
		t.setPrice(100);
		t.setTicketNumber("T26");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(26));
		this.dao.save(t);

		t = new Ticket();
		t.setId(27);
		t.setPrice(100);
		t.setTicketNumber("T27");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(27));
		this.dao.save(t);

		t = new Ticket();
		t.setId(28);
		t.setPrice(100);
		t.setTicketNumber("T28");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(28));
		this.dao.save(t);

		t = new Ticket();
		t.setId(29);
		t.setPrice(100);
		t.setTicketNumber("T29");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(29));
		this.dao.save(t);

		t = new Ticket();
		t.setId(30);
		t.setPrice(100);
		t.setTicketNumber("T30");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(30));
		this.dao.save(t);

		//
		// PERFORMANCE 2
		//
		t = new Ticket();
		t.setId(31);
		t.setPrice(100);
		t.setTicketNumber("T16");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(32);
		t.setPrice(100);
		t.setTicketNumber("T17");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);

		t = new Ticket();
		t.setId(33);
		t.setPrice(100);
		t.setTicketNumber("T18");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);

		t = new Ticket();
		t.setId(34);
		t.setPrice(100);
		t.setTicketNumber("T19");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);

		t = new Ticket();
		t.setId(35);
		t.setPrice(100);
		t.setTicketNumber("T20");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);

		t = new Ticket();
		t.setId(36);
		t.setPrice(100);
		t.setTicketNumber("T21");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);

		t = new Ticket();
		t.setId(37);
		t.setPrice(100);
		t.setTicketNumber("T22");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(7));
		this.dao.save(t);

		t = new Ticket();
		t.setId(38);
		t.setPrice(100);
		t.setTicketNumber("T23");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(8));
		this.dao.save(t);

		t = new Ticket();
		t.setId(39);
		t.setPrice(100);
		t.setTicketNumber("T24");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(9));
		this.dao.save(t);

		t = new Ticket();
		t.setId(40);
		t.setPrice(100);
		t.setTicketNumber("T25");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(10));
		this.dao.save(t);

		t = new Ticket();
		t.setId(41);
		t.setPrice(100);
		t.setTicketNumber("T26");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(11));
		this.dao.save(t);

		t = new Ticket();
		t.setId(42);
		t.setPrice(100);
		t.setTicketNumber("T27");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(43);
		t.setPrice(100);
		t.setTicketNumber("T28");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(44);
		t.setPrice(100);
		t.setTicketNumber("T29");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(13));
		this.dao.save(t);

		t = new Ticket();
		t.setId(45);
		t.setPrice(100);
		t.setTicketNumber("T30");
		t.setPerformance(this.pdao.findOne(2));
		t.setSeat(this.sdao.findOne(14));
		this.dao.save(t);

		//
		// PERFORMANCE 3
		//
		t = new Ticket();
		t.setId(46);
		t.setPrice(100);
		t.setTicketNumber("T31");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(47);
		t.setPrice(100);
		t.setTicketNumber("T32");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);

		t = new Ticket();
		t.setId(48);
		t.setPrice(100);
		t.setTicketNumber("T33");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);

		t = new Ticket();
		t.setId(49);
		t.setPrice(100);
		t.setTicketNumber("T34");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);

		t = new Ticket();
		t.setId(50);
		t.setPrice(100);
		t.setTicketNumber("T35");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);

		t = new Ticket();
		t.setId(51);
		t.setPrice(100);
		t.setTicketNumber("T36");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);

		t = new Ticket();
		t.setId(52);
		t.setPrice(100);
		t.setTicketNumber("T37");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(7));
		this.dao.save(t);

		t = new Ticket();
		t.setId(53);
		t.setPrice(100);
		t.setTicketNumber("T38");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(8));
		this.dao.save(t);

		t = new Ticket();
		t.setId(54);
		t.setPrice(100);
		t.setTicketNumber("T39");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(9));
		this.dao.save(t);

		t = new Ticket();
		t.setId(55);
		t.setPrice(100);
		t.setTicketNumber("T40");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(10));
		this.dao.save(t);

		t = new Ticket();
		t.setId(56);
		t.setPrice(100);
		t.setTicketNumber("T41");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(11));
		this.dao.save(t);

		t = new Ticket();
		t.setId(57);
		t.setPrice(100);
		t.setTicketNumber("T42");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(58);
		t.setPrice(100);
		t.setTicketNumber("T43");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(59);
		t.setPrice(100);
		t.setTicketNumber("T44");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(13));
		this.dao.save(t);

		t = new Ticket();
		t.setId(60);
		t.setPrice(100);
		t.setTicketNumber("T45");
		t.setPerformance(this.pdao.findOne(3));
		t.setSeat(this.sdao.findOne(14));
		this.dao.save(t);

		//
		// PERFORMANCE 4
		//
		t = new Ticket();
		t.setId(61);
		t.setPrice(100);
		t.setTicketNumber("T46");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(62);
		t.setPrice(100);
		t.setTicketNumber("T47");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);

		t = new Ticket();
		t.setId(63);
		t.setPrice(100);
		t.setTicketNumber("T48");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);

		t = new Ticket();
		t.setId(64);
		t.setPrice(100);
		t.setTicketNumber("T49");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);

		t = new Ticket();
		t.setId(65);
		t.setPrice(100);
		t.setTicketNumber("T50");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);

		t = new Ticket();
		t.setId(66);
		t.setPrice(100);
		t.setTicketNumber("T51");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);

		t = new Ticket();
		t.setId(67);
		t.setPrice(100);
		t.setTicketNumber("T52");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(7));
		this.dao.save(t);

		t = new Ticket();
		t.setId(68);
		t.setPrice(100);
		t.setTicketNumber("T53");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(8));
		this.dao.save(t);

		t = new Ticket();
		t.setId(69);
		t.setPrice(100);
		t.setTicketNumber("T54");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(9));
		this.dao.save(t);

		t = new Ticket();
		t.setId(70);
		t.setPrice(100);
		t.setTicketNumber("T55");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(10));
		this.dao.save(t);

		t = new Ticket();
		t.setId(71);
		t.setPrice(100);
		t.setTicketNumber("T56");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(11));
		this.dao.save(t);

		t = new Ticket();
		t.setId(72);
		t.setPrice(100);
		t.setTicketNumber("T57");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(73);
		t.setPrice(100);
		t.setTicketNumber("T58");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(74);
		t.setPrice(100);
		t.setTicketNumber("T59");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(13));
		this.dao.save(t);

		t = new Ticket();
		t.setId(75);
		t.setPrice(100);
		t.setTicketNumber("T60");
		t.setPerformance(this.pdao.findOne(4));
		t.setSeat(this.sdao.findOne(14));
		this.dao.save(t);

		//
		// PERFORMANCE 5
		//
		t = new Ticket();
		t.setId(76);
		t.setPrice(100);
		t.setTicketNumber("T61");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(77);
		t.setPrice(100);
		t.setTicketNumber("T62");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);

		t = new Ticket();
		t.setId(78);
		t.setPrice(100);
		t.setTicketNumber("T63");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);

		t = new Ticket();
		t.setId(79);
		t.setPrice(100);
		t.setTicketNumber("T64");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);

		t = new Ticket();
		t.setId(80);
		t.setPrice(100);
		t.setTicketNumber("T65");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);

		t = new Ticket();
		t.setId(81);
		t.setPrice(100);
		t.setTicketNumber("T66");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);

		t = new Ticket();
		t.setId(82);
		t.setPrice(100);
		t.setTicketNumber("T67");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(7));
		this.dao.save(t);

		t = new Ticket();
		t.setId(83);
		t.setPrice(100);
		t.setTicketNumber("T68");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(8));
		this.dao.save(t);

		t = new Ticket();
		t.setId(84);
		t.setPrice(100);
		t.setTicketNumber("T69");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(9));
		this.dao.save(t);

		t = new Ticket();
		t.setId(85);
		t.setPrice(100);
		t.setTicketNumber("T70");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(10));
		this.dao.save(t);

		t = new Ticket();
		t.setId(86);
		t.setPrice(100);
		t.setTicketNumber("T71");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(11));
		this.dao.save(t);

		t = new Ticket();
		t.setId(87);
		t.setPrice(100);
		t.setTicketNumber("T72");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(88);
		t.setPrice(100);
		t.setTicketNumber("T73");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(89);
		t.setPrice(100);
		t.setTicketNumber("T74");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(13));
		this.dao.save(t);

		t = new Ticket();
		t.setId(90);
		t.setPrice(100);
		t.setTicketNumber("T75");
		t.setPerformance(this.pdao.findOne(5));
		t.setSeat(this.sdao.findOne(14));
		this.dao.save(t);

		//
		// PERFORMANCE 6
		//
		t = new Ticket();
		t.setId(91);
		t.setPrice(100);
		t.setTicketNumber("T76");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(92);
		t.setPrice(100);
		t.setTicketNumber("T77");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);

		t = new Ticket();
		t.setId(93);
		t.setPrice(100);
		t.setTicketNumber("T78");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);

		t = new Ticket();
		t.setId(94);
		t.setPrice(100);
		t.setTicketNumber("T79");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);

		t = new Ticket();
		t.setId(95);
		t.setPrice(100);
		t.setTicketNumber("T80");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);

		t = new Ticket();
		t.setId(96);
		t.setPrice(100);
		t.setTicketNumber("T81");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);

		t = new Ticket();
		t.setId(97);
		t.setPrice(100);
		t.setTicketNumber("T82");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(7));
		this.dao.save(t);

		t = new Ticket();
		t.setId(98);
		t.setPrice(100);
		t.setTicketNumber("T83");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(8));
		this.dao.save(t);

		t = new Ticket();
		t.setId(99);
		t.setPrice(100);
		t.setTicketNumber("T84");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(9));
		this.dao.save(t);

		t = new Ticket();
		t.setId(100);
		t.setPrice(100);
		t.setTicketNumber("T85");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(10));
		this.dao.save(t);

		t = new Ticket();
		t.setId(101);
		t.setPrice(100);
		t.setTicketNumber("T86");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(11));
		this.dao.save(t);

		t = new Ticket();
		t.setId(102);
		t.setPrice(100);
		t.setTicketNumber("T87");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(103);
		t.setPrice(100);
		t.setTicketNumber("T88");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(12));
		this.dao.save(t);

		t = new Ticket();
		t.setId(104);
		t.setPrice(100);
		t.setTicketNumber("T89");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(13));
		this.dao.save(t);

		t = new Ticket();
		t.setId(105);
		t.setPrice(100);
		t.setTicketNumber("T90");
		t.setPerformance(this.pdao.findOne(6));
		t.setSeat(this.sdao.findOne(14));
		this.dao.save(t);

		//
		// PERFORMANCE 2
		//
		t = new Ticket();
		t.setId(106);
		t.setPrice(100);
		t.setTicketNumber("T92");
		t.setPerformance(this.pdao.findOne(7));
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(107);
		t.setPrice(100);
		t.setTicketNumber("T93");
		t.setPerformance(this.pdao.findOne(7));
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);

		t = new Ticket();
		t.setId(108);
		t.setPrice(100);
		t.setTicketNumber("T94");
		t.setPerformance(this.pdao.findOne(7));
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);

		//
		// MOVIES
		//
		p=pdao.findOne(8);
		
		t = new Ticket();
		t.setId(109);
		t.setPrice(100);
		t.setTicketNumber("T95");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(110);
		t.setPrice(100);
		t.setTicketNumber("T96");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(111);
		t.setPrice(100);
		t.setTicketNumber("T97");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(112);
		t.setPrice(100);
		t.setTicketNumber("T98");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(111);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(112);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		
		p=pdao.findOne(10);
		
		t = new Ticket();
		t.setId(113);
		t.setPrice(100);
		t.setTicketNumber("T95");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(114);
		t.setPrice(100);
		t.setTicketNumber("T96");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(115);
		t.setPrice(100);
		t.setTicketNumber("T97");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(116);
		t.setPrice(100);
		t.setTicketNumber("T98");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(117);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
p=pdao.findOne(12);		
		t = new Ticket();
		t.setId(118);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(119);
		t.setPrice(100);
		t.setTicketNumber("T95");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(1));
		this.dao.save(t);

		t = new Ticket();
		t.setId(120);
		t.setPrice(100);
		t.setTicketNumber("T96");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(2));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(121);
		t.setPrice(100);
		t.setTicketNumber("T97");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(3));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(122);
		t.setPrice(100);
		t.setTicketNumber("T98");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(4));
		this.dao.save(t);
		
		p=pdao.findOne(13);
		t = new Ticket();
		t.setId(123);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(124);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(125);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(126);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		p=pdao.findOne(14);
		t = new Ticket();
		t.setId(127);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(128);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(129);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		p=pdao.findOne(15);
		t = new Ticket();
		t.setId(130);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(131);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(132);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		p=pdao.findOne(16);
		t = new Ticket();
		t.setId(133);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(134);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		p=pdao.findOne(17);
		t = new Ticket();
		t.setId(135);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
		t = new Ticket();
		t.setId(136);
		t.setPrice(100);
		t.setTicketNumber("T100");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(6));
		this.dao.save(t);
		
		p=pdao.findOne(18);
		t = new Ticket();
		t.setId(137);
		t.setPrice(100);
		t.setTicketNumber("T99");
		t.setPerformance(p);
		t.setSeat(this.sdao.findOne(5));
		this.dao.save(t);
		
	}
}
