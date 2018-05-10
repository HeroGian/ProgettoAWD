package ml.canecarroarmato.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ml.canecarroarmato.model.Blog;


@Repository
public interface BlogRepository extends CrudRepository<Blog, Long>{
	
	@Query("select b "
			+ "from Blog b "
			+ "join b.owner as a "
			+ "where a.userid = :userid")
	Blog findByUserId(@Param("userid") int userid);
	Blog findByBlogid(int blogid);
}
