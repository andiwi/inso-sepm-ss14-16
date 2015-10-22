package at.ac.tuwien.inso.tl.server.integrationtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.inso.tl.model.Article;
import at.ac.tuwien.inso.tl.server.exception.ServiceException;
import at.ac.tuwien.inso.tl.server.service.ArticleService;

public class ArticleServiceIntegrationTest extends AbstractServiceIntegrationTest
{
	@Autowired
	private ArticleService articleService;
	
	@Before
	public void setUp(){}

	@Test
	public void findById_returnsNullIfNotExists() throws ServiceException {
		assertThat(this.articleService.findArticleById(100), is(nullValue()));
	}

	@Test
	public void findById_returnsArticleIfExists() throws ServiceException {
		Article a = this.articleService.findArticleById(1); 
		assertThat(a.getId(), is(1));
	}

	@Test
	public void findArticles_returnsAllIfSearchIsNull() throws ServiceException { 
		assertThat(this.articleService.findArticles(null), hasSize(3));
	}

	@Test
	public void findArticles_returnsAllIfSearchIsEmpty() throws ServiceException { 
		assertThat(this.articleService.findArticles(""), hasSize(3));
	}

	@Test
	public void findArticles_searchesInTitle() throws ServiceException { 
		assertThat(this.articleService.findArticles("titel"), hasSize(2));
	}

	@Test
	public void findArticles_searchesInDescription() throws ServiceException { 
		assertThat(this.articleService.findArticles("esch"), hasSize(1));
	}

	@Test
	public void findArticles_searchesInDescriptionCaseInsensitive() throws ServiceException { 
		assertThat(this.articleService.findArticles("EsCh"), hasSize(1));
	}

	@Test
	public void findArticles_searchesInTitleCaseInsensitive() throws ServiceException { 
		assertThat(this.articleService.findArticles("ItE"), hasSize(2));
	} 
}
