package at.ac.tuwien.inso.tl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.Article;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer>
{
	@Query(value = "SELECT a " + "FROM Article a " + "WHERE a.id = :id")
	public Article findArticleById(@Param("id") Integer id);
	
	@Query("SELECT a "
			+ "FROM Article a "
			+ "WHERE UPPER(a.description) like %:searchTerm% or "
			+ "UPPER(a.title) like %:searchTerm% "
			+ "ORDER BY a.id ASC")
	public List<Article> findArticlesByName(@Param("searchTerm") String searchTerm);
}
