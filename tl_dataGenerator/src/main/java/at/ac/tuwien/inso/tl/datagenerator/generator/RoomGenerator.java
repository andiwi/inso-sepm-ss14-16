package at.ac.tuwien.inso.tl.datagenerator.generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.LocationDao;
import at.ac.tuwien.inso.tl.dao.RoomDao;
import at.ac.tuwien.inso.tl.model.Room;

@Component
public class RoomGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(RoomGenerator.class);

	@Autowired
	RoomDao dao;

	@Autowired
	LocationDao ldao;

	public void generate()
	{
		LOG.info("+++++ Generate Room Data +++++");

		Room r = new Room();
		r.setId(1);
		r.setTitle("Hauptsaal");
		r.setLocation(ldao.findOne(1));
		this.dao.save(r);

		r = new Room();
		r.setId(2);
		r.setTitle("Hauptsaal");
		r.setLocation(ldao.findOne(2));
		this.dao.save(r);

		r = new Room();
		r.setId(3);
		r.setTitle("Flex Cafe");
		r.setLocation(ldao.findOne(3));
		this.dao.save(r);

		r = new Room();
		r.setId(4);
		r.setTitle("Main Floor");
		r.setLocation(ldao.findOne(3));
		this.dao.save(r);
	}
}
