package at.ac.tuwien.inso.tl.datagenerator.generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.RoomDao;
import at.ac.tuwien.inso.tl.dao.SeatDao;
import at.ac.tuwien.inso.tl.model.Room;
import at.ac.tuwien.inso.tl.model.Seat;

@Component
public class SeatGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(SeatGenerator.class);

	@Autowired
	SeatDao dao;

	@Autowired
	RoomDao rdao;

	public void generate()
	{
		LOG.info("+++++ Generate Seat Data +++++");

		Room r = rdao.findOne(1);

		// row 1
		Seat s = new Seat();
		s.setId(1);
		s.setSeatNumber("1A");
		s.setRow(0);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(2);
		s.setSeatNumber("1B");
		s.setRow(0);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(3);
		s.setSeatNumber("1C");
		s.setRow(0);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(4);
		s.setSeatNumber("1D");
		s.setRow(0);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(5);
		s.setSeatNumber("1E");
		s.setRow(0);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(6);
		s.setSeatNumber("1F");
		s.setRow(0);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		// row 2
		s = new Seat();
		s.setId(7);
		s.setSeatNumber("2A");
		s.setRow(1);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(8);
		s.setSeatNumber("2B");
		s.setRow(1);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(9);
		s.setSeatNumber("2C");
		s.setRow(1);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(10);
		s.setSeatNumber("2D");
		s.setRow(1);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(11);
		s.setSeatNumber("2E");
		s.setRow(1);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(12);
		s.setSeatNumber("2F");
		s.setRow(1);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(13);
		s.setSeatNumber("2G");
		s.setRow(1);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(14);
		s.setSeatNumber("2H");
		s.setRow(1);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 3 empty

		// row 4
		s = new Seat();
		s.setId(15);
		s.setSeatNumber("4A");
		s.setRow(3);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(16);
		s.setSeatNumber("4B");
		s.setRow(3);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(17);
		s.setSeatNumber("4C");
		s.setRow(3);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(18);
		s.setSeatNumber("4D");
		s.setRow(3);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(19);
		s.setSeatNumber("4E");
		s.setRow(3);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(20);
		s.setSeatNumber("4F");
		s.setRow(3);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(21);
		s.setSeatNumber("4G");
		s.setRow(3);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(22);
		s.setSeatNumber("4H");
		s.setRow(3);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 5
		s = new Seat();
		s.setId(23);
		s.setSeatNumber("5A");
		s.setRow(4);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(24);
		s.setSeatNumber("5B");
		s.setRow(4);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(25);
		s.setSeatNumber("5C");
		s.setRow(4);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(26);
		s.setSeatNumber("5D");
		s.setRow(4);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(27);
		s.setSeatNumber("5E");
		s.setRow(4);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(28);
		s.setSeatNumber("5F");
		s.setRow(4);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(29);
		s.setSeatNumber("5G");
		s.setRow(4);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(30);
		s.setSeatNumber("5H");
		s.setRow(4);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		r = rdao.findOne(2);

		// row 1
		s = new Seat();
		s.setId(31);
		s.setSeatNumber("1A");
		s.setRow(0);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(32);
		s.setSeatNumber("1B");
		s.setRow(0);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(33);
		s.setSeatNumber("1C");
		s.setRow(0);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(34);
		s.setSeatNumber("1D");
		s.setRow(0);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(35);
		s.setSeatNumber("1E");
		s.setRow(0);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(36);
		s.setSeatNumber("1F");
		s.setRow(0);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		// row 2
		s = new Seat();
		s.setId(37);
		s.setSeatNumber("2A");
		s.setRow(1);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(38);
		s.setSeatNumber("2B");
		s.setRow(1);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(39);
		s.setSeatNumber("2C");
		s.setRow(1);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(40);
		s.setSeatNumber("2D");
		s.setRow(1);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(41);
		s.setSeatNumber("2E");
		s.setRow(1);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(42);
		s.setSeatNumber("2F");
		s.setRow(1);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(43);
		s.setSeatNumber("2G");
		s.setRow(1);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(44);
		s.setSeatNumber("2H");
		s.setRow(1);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 3 empty

		// row 4
		s = new Seat();
		s.setId(45);
		s.setSeatNumber("4A");
		s.setRow(3);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(46);
		s.setSeatNumber("4B");
		s.setRow(3);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(47);
		s.setSeatNumber("4C");
		s.setRow(3);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(48);
		s.setSeatNumber("4D");
		s.setRow(3);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(49);
		s.setSeatNumber("4E");
		s.setRow(3);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(50);
		s.setSeatNumber("4F");
		s.setRow(3);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(51);
		s.setSeatNumber("4G");
		s.setRow(3);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(52);
		s.setSeatNumber("4H");
		s.setRow(3);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 5
		s = new Seat();
		s.setId(53);
		s.setSeatNumber("5A");
		s.setRow(4);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(54);
		s.setSeatNumber("5B");
		s.setRow(4);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(55);
		s.setSeatNumber("5C");
		s.setRow(4);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(56);
		s.setSeatNumber("5D");
		s.setRow(4);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(57);
		s.setSeatNumber("5E");
		s.setRow(4);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(58);
		s.setSeatNumber("5F");
		s.setRow(4);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(59);
		s.setSeatNumber("5G");
		s.setRow(4);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(60);
		s.setSeatNumber("5H");
		s.setRow(4);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		r = rdao.findOne(3);

		// row 1
		s = new Seat();
		s.setId(61);
		s.setSeatNumber("1A");
		s.setRow(0);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(62);
		s.setSeatNumber("1B");
		s.setRow(0);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(63);
		s.setSeatNumber("1C");
		s.setRow(0);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(64);
		s.setSeatNumber("1D");
		s.setRow(0);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(65);
		s.setSeatNumber("1E");
		s.setRow(0);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(66);
		s.setSeatNumber("1F");
		s.setRow(0);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		// row 2
		s = new Seat();
		s.setId(67);
		s.setSeatNumber("2A");
		s.setRow(1);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(68);
		s.setSeatNumber("2B");
		s.setRow(1);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(69);
		s.setSeatNumber("2C");
		s.setRow(1);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(70);
		s.setSeatNumber("2D");
		s.setRow(1);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(71);
		s.setSeatNumber("2E");
		s.setRow(1);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(72);
		s.setSeatNumber("2F");
		s.setRow(1);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(73);
		s.setSeatNumber("2G");
		s.setRow(1);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(74);
		s.setSeatNumber("2H");
		s.setRow(1);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 3 empty

		// row 4
		s = new Seat();
		s.setId(75);
		s.setSeatNumber("4A");
		s.setRow(3);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(76);
		s.setSeatNumber("4B");
		s.setRow(3);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(77);
		s.setSeatNumber("4C");
		s.setRow(3);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(78);
		s.setSeatNumber("4D");
		s.setRow(3);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(79);
		s.setSeatNumber("4E");
		s.setRow(3);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(80);
		s.setSeatNumber("4F");
		s.setRow(3);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(81);
		s.setSeatNumber("4G");
		s.setRow(3);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(82);
		s.setSeatNumber("4H");
		s.setRow(3);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 5
		s = new Seat();
		s.setId(83);
		s.setSeatNumber("5A");
		s.setRow(4);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(84);
		s.setSeatNumber("5B");
		s.setRow(4);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(85);
		s.setSeatNumber("5C");
		s.setRow(4);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(86);
		s.setSeatNumber("5D");
		s.setRow(4);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(87);
		s.setSeatNumber("5E");
		s.setRow(4);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(88);
		s.setSeatNumber("5F");
		s.setRow(4);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(89);
		s.setSeatNumber("5G");
		s.setRow(4);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(90);
		s.setSeatNumber("5H");
		s.setRow(4);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		r = rdao.findOne(4);

		// row 1
		s = new Seat();
		s.setId(91);
		s.setSeatNumber("1A");
		s.setRow(0);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(92);
		s.setSeatNumber("1B");
		s.setRow(0);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(93);
		s.setSeatNumber("1C");
		s.setRow(0);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(94);
		s.setSeatNumber("1D");
		s.setRow(0);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(95);
		s.setSeatNumber("1E");
		s.setRow(0);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(96);
		s.setSeatNumber("1F");
		s.setRow(0);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		// row 2
		s = new Seat();
		s.setId(97);
		s.setSeatNumber("2A");
		s.setRow(1);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(98);
		s.setSeatNumber("2B");
		s.setRow(1);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(99);
		s.setSeatNumber("2C");
		s.setRow(1);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(100);
		s.setSeatNumber("2D");
		s.setRow(1);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(101);
		s.setSeatNumber("2E");
		s.setRow(1);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(102);
		s.setSeatNumber("2F");
		s.setRow(1);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(103);
		s.setSeatNumber("2G");
		s.setRow(1);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(104);
		s.setSeatNumber("2H");
		s.setRow(1);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 3 empty

		// row 4
		s = new Seat();
		s.setId(105);
		s.setSeatNumber("4A");
		s.setRow(3);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(106);
		s.setSeatNumber("4B");
		s.setRow(3);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(107);
		s.setSeatNumber("4C");
		s.setRow(3);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(108);
		s.setSeatNumber("4D");
		s.setRow(3);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(109);
		s.setSeatNumber("4E");
		s.setRow(3);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(110);
		s.setSeatNumber("4F");
		s.setRow(3);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(111);
		s.setSeatNumber("4G");
		s.setRow(3);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(112);
		s.setSeatNumber("4H");
		s.setRow(3);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);

		// row 5
		s = new Seat();
		s.setId(113);
		s.setSeatNumber("5A");
		s.setRow(4);
		s.setColumn(0);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(114);
		s.setSeatNumber("5B");
		s.setRow(4);
		s.setColumn(1);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(115);
		s.setSeatNumber("5C");
		s.setRow(4);
		s.setColumn(2);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(116);
		s.setSeatNumber("5D");
		s.setRow(4);
		s.setColumn(3);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(117);
		s.setSeatNumber("5E");
		s.setRow(4);
		s.setColumn(5);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(118);
		s.setSeatNumber("5F");
		s.setRow(4);
		s.setColumn(6);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(119);
		s.setSeatNumber("5G");
		s.setRow(4);
		s.setColumn(7);
		s.setRoom(r);
		this.dao.save(s);

		s = new Seat();
		s.setId(120);
		s.setSeatNumber("5H");
		s.setRow(4);
		s.setColumn(8);
		s.setRoom(r);
		this.dao.save(s);
	}
}
