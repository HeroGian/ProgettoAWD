package ml.canecarroarmato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ml.canecarroarmato.model.Comment;
import ml.canecarroarmato.model.Post;
import ml.canecarroarmato.model.User;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	
	List<Comment> findByPost(Post post);
	
	@Query("select c "
			+ "from Comment c "
			+ "where c.post = :post and c.replyto is null")
	List<Comment> findAllParentCommentsByPost(@Param("post") Post post);
	
	@Query("select c "
			+ "from Comment c "
			+ "where c.post = :post and c.replyto = :reply")
	List<Comment> findAllRepliesByPost(@Param("post") Post post, @Param("reply") Comment reply);
	
	List<Comment> findTop5ByOrderByPubdateDesc();
	List<Comment> findTop5ByAuthorOrderByPubdateDesc(User author);
	
	Comment findByCommentid(int commentid);
	
	Long countByPost(Post post);
	Long countByAuthor(User author);
}
