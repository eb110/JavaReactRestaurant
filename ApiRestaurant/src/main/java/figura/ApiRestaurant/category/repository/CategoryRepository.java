package figura.ApiRestaurant.category.repository;

import figura.ApiRestaurant.category.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {


}
