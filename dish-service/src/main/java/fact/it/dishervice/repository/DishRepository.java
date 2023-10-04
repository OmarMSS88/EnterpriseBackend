package fact.it.dishervice.repository;

import fact.it.dishervice.model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DishRepository extends MongoRepository<Dish, String> {
    List<Dish> findByDishNrIn(List<String> dishNr);
}
