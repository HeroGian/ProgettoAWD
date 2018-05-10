package ml.canecarroarmato.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ml.canecarroarmato.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	Category findByCatid(int catid);
}
