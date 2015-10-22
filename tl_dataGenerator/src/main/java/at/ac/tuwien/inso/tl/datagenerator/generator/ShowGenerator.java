package at.ac.tuwien.inso.tl.datagenerator.generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.ShowDao;
import at.ac.tuwien.inso.tl.model.Show;
import at.ac.tuwien.inso.tl.model.ShowType;

@Component
public class ShowGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(ShowGenerator.class);

	@Autowired
	ShowDao dao;

	public void generate()
	{
		LOG.info("+++++ Generate Show Data +++++");

		Show s = new Show();
		s.setId(1);
		s.setTitle("Thor");
		s.setDescription("When Jane Foster is possessed by a great power, Thor must protect her from a new threat of old times: the Dark Elves.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);

		s = new Show();
		s.setId(2);
		s.setTitle("Beatpatrol");
		s.setDescription("Ein-tägiges elektronisches Festival im VAZ St. Pölten.");
		s.setShowType(ShowType.FESTIVAL);
		this.dao.save(s);

		s = new Show();
		s.setId(3);
		s.setTitle("Frequency");
		s.setDescription("Im VAZ St. Pölten geht's rund, mit zahlreichen Live Acts.");
		s.setShowType(ShowType.FESTIVAL);
		this.dao.save(s);

		s = new Show();
		s.setId(4);
		s.setTitle("Electric Love");
		s.setDescription("Elektronische Liebesmusik am wunderschönen Schwarzlsee.");
		s.setShowType(ShowType.FESTIVAL);
		this.dao.save(s);

		s = new Show();
		s.setId(5);
		s.setTitle("Nova Rock");
		s.setDescription("Rock Acts en Masse auf einer Wiese.");
		s.setShowType(ShowType.FESTIVAL);
		this.dao.save(s);
		
		/*
		 * MOVIES
		 */
		s = new Show();
		s.setId(6);
		s.setTitle("Avatar");
		s.setDescription("A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);

		s = new Show();
		s.setId(7);
		s.setTitle("Iron Man 2");
		s.setDescription("With the world now aware of his identity as Iron Man, Tony Stark must contend with both his declining health and a vengeful mad man with ties to his father's legacy.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);
		
		s = new Show();
		s.setId(8);
		s.setTitle("Shutter Island");
		s.setDescription("In 1954, U.S. Marshal Teddy Daniels is investigating the disappearance of a murderess who escaped from a hospital for the criminally insane and is presumed to be hiding nearby.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);

		s = new Show();
		s.setId(9);
		s.setTitle("Superbad");
		s.setDescription("Two co-dependent high school seniors are forced to deal with separation anxiety after their plan to stage a booze-soaked party goes awry.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);
		
		s = new Show();
		s.setId(10);
		s.setTitle("The Longest Yard");
		s.setDescription("Prison inmates form a football team to challenge the prison guards.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);

		s = new Show();
		s.setId(11);
		s.setTitle("The Blind Side");
		s.setDescription("The story of Michael Oher, a homeless and traumatized boy who became an All American football player and first round NFL draft pick with the help of a caring woman and her family.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);
		
		s = new Show();
		s.setId(12);
		s.setTitle("Any Given Sunday");
		s.setDescription("A behind-the-scenes look at the life-and-death struggles of modern-day gladiators and those who lead them.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);

		s = new Show();
		s.setId(13);
		s.setTitle("Draft Day");
		s.setDescription("At the NFL Draft, general manager Sonny Weaver has the opportunity to rebuild his team when he trades for the number one pick. He must decide what he's willing to sacrifice on a life-changing day for a few hundred young men with NFL dreams.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);
		
		s = new Show();
		s.setId(14);
		s.setTitle("Die 12 Geschworenen");
		s.setDescription("A dissenting juror in a murder trial slowly manages to convince the others that the case is not as obviously clear as it seemed in court.");
		s.setShowType(ShowType.MOVIE);
		this.dao.save(s);

		s = new Show();
		s.setId(15);
		s.setTitle("Electric Daisy Karnival");
		s.setDescription("Karnival Party Festival, Verkleidungen und gute Musik.");
		s.setShowType(ShowType.FESTIVAL);
		this.dao.save(s);
	}
}
