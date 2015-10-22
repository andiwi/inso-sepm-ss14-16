package at.ac.tuwien.inso.tl.datagenerator.generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import at.ac.tuwien.inso.tl.dao.ArticleDao;
import at.ac.tuwien.inso.tl.dao.ShowDao;
import at.ac.tuwien.inso.tl.model.Article;

@Component
public class ArticleGenerator implements DataGenerator
{
	private static final Logger LOG = Logger.getLogger(ArticleGenerator.class);

	@Autowired
	ArticleDao dao;

	@Autowired
	ShowDao sdao;

	public void generate()
	{
		LOG.info("+++++ Generate Article Data +++++");

		Article a = new Article();
		a.setId(1);
		a.setAvailable(1);
		a.setTitle("Ticketline Schlüsselanhänger");
		a.setDescription("Ein Schlüsselanhänger mit dem Aufdruck 'Ticketline'");
		a.setPrice(100);
		a.setShow(sdao.findOne(1));
		dao.save(a);

		a = new Article();
		a.setId(2);
		a.setAvailable(1);
		a.setTitle("Ticketline T-Shirt S");
		a.setDescription("Ein T-Shirt mit dem Aufdruck 'Ticketline' in S");
		a.setPrice(300);
		a.setShow(sdao.findOne(1));
		dao.save(a);

		a = new Article();
		a.setId(3);
		a.setAvailable(1);
		a.setTitle("Ticketline T-Shirt M");
		a.setDescription("Ein T-Shirt mit dem Aufdruck 'Ticketline' in M");
		a.setPrice(300);
		a.setShow(sdao.findOne(1));
		dao.save(a);

		a = new Article();
		a.setId(4);
		a.setAvailable(1);
		a.setTitle("Ticketline T-Shirt L");
		a.setDescription("Ein T-Shirt mit dem Aufdruck 'Ticketline' in L");
		a.setPrice(300);
		a.setShow(sdao.findOne(1));
		dao.save(a);
		
		a = new Article();
		a.setId(5);
		a.setAvailable(1);
		a.setTitle("Ticketline Bär");
		a.setDescription("Ein Plüschbär mit einem Shirt mit dem Aufdruck 'Ticketline'");
		a.setPrice(200);
		a.setShow(sdao.findOne(1));
		dao.save(a);

	}

}
