/**
 * 
 */
package at.ac.tuwien.inso.tl.datagenerator.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.ArtistDao;
import at.ac.tuwien.inso.tl.dao.ParticipationDao;
import at.ac.tuwien.inso.tl.dao.ShowDao;
import at.ac.tuwien.inso.tl.model.Artist;
import at.ac.tuwien.inso.tl.model.Participation;

/**
 * @author beks
 * 
 */
@Component
public class ArtistGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(ArtistGenerator.class);

	@Autowired
	private ArtistDao dao;

	@Autowired
	ParticipationDao pdao;

	@Autowired
	ShowDao sdao;

	@Override
	public void generate()
	{
		LOG.info("+++++ Generate Artist Data +++++");

		Artist art = new Artist();
		Participation participations = new Participation();
		List<Participation> partList = new ArrayList<Participation>();

		art.setId(1);
		art.setFirstname("Megan");
		art.setLastname("Fox");
		art.setDescription("http://www.imdb.com/name/nm1083271/bio");
		participations.setArtist(art);
		participations.setArtistRole("Actress");
		participations.setId(1);
		this.dao.save(art);
		participations.setShow(sdao.findOne(1));
		this.pdao.save(participations);
		partList.add(participations);
		art.setParticipations(partList);
		LOG.info("----------------Debugging ArtistGenerator: " + art.getParticipations().size());
		this.dao.save(art);

		art = new Artist();
		participations = new Participation();
		partList = new ArrayList<Participation>();

		art.setId(2);
		art.setFirstname("Kevin");
		art.setLastname("James");
		art.setDescription("http://www.imdb.com/name/nm0416673/bio");
		participations.setArtist(art);
		participations.setArtistRole("Actor");
		participations.setId(2);
		this.dao.save(art);
		participations.setShow(sdao.findOne(5));
		this.pdao.save(participations);
		partList.add(participations);
		art.setParticipations(partList);
		this.dao.save(art);

		art = new Artist();
		participations = new Participation();
		partList = new ArrayList<Participation>();

		art.setId(3);
		art.setFirstname("Alicia");
		art.setLastname("Keys");
		art.setDescription("http://www.imdb.com/name/nm1006024/bio");
		participations.setArtist(art);
		participations.setArtistRole("Musician");
		participations.setId(3);
		this.dao.save(art);
		participations.setShow(sdao.findOne(3));
		this.pdao.save(participations);
		partList.add(participations);
		art.setParticipations(partList);
		this.dao.save(art);
	}
}
