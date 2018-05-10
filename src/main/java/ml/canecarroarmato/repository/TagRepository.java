package ml.canecarroarmato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ml.canecarroarmato.model.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{
	
	Tag findByTitle(String title);
	
	@Query("select t "
			+ "from Post p "
			+ "join p.tagslist t "
			+ "where p.postid = :postid")
	List<Tag> findByPostid(@Param("postid") int postid);
}
