package fact.it.dishservice.repository;

import fact.it.dishservice.model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DishRepository extends MongoRepository<Dish, String> {
    List<Dish> findByDishNrIn(List<String> dishNr);
}
