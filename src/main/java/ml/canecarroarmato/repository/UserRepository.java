package ml.canecarroarmato.repository;

import ml.canecarroarmato.model.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* Per definire query personalizzate
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findByName(String name);
	User findByUsernameIgnoreCase(String username);
	User findByUserid(int userid);
}
