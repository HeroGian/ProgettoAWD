package ml.canecarroarmato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ml.canecarroarmato.model.Blog;
import ml.canecarroarmato.model.Post;


@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
	
	Post findByPostid(int postid);
	Post findByTitle(String title);
	
	@Query("select p "
			+ "from Tag t "
			+ "join t.posts p "
			+ "where t.tagid = :tagid and "
			+ 		 "p.postid != :postid "
			+ "order by pubdate desc")
	List<Post> findByTagid(@Param("tagid") int tagid, @Param("postid") int postid);
	List<Post> findByOwnerblog(Blog ownerblog);	
	List<Post> findTop10ByOrderByPubdateDesc();
	
	@Query("select p "
			+ "from Post p "
			+ "where upper(p.title) like :subs% "
			+ "order by p.pubdate desc")
	List<Post> findBySubstring(@Param("subs") String subs);
}
