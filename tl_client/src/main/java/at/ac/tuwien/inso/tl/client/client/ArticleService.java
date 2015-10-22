package at.ac.tuwien.inso.tl.client.client;

import java.util.List;

import at.ac.tuwien.inso.tl.client.exception.ServiceException;
import at.ac.tuwien.inso.tl.dto.ArticleDto;

public interface ArticleService
{

	public ArticleDto findArticleById(ArticleDto article) throws ServiceException;

	public List<ArticleDto> findArticles(String search) throws ServiceException;

}
