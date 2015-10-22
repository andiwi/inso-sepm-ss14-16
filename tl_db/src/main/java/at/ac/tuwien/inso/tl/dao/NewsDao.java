package at.ac.tuwien.inso.tl.dao;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.inso.tl.model.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer>
{

	@Query(value = "SELECT n FROM News n ORDER BY n.createdOn DESC")
	public List<News> findAllOrderByCreatedOnDesc();

	@Query(value = "SELECT n FROM News n WHERE n.createdOn > :date ORDER BY n.createdOn DESC")
	public List<News> findByDateOrderByCreatedOnDesc(@Param("date") Date date);

}
