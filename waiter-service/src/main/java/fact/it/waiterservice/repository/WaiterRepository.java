package fact.it.waiterservice.repository;

import fact.it.waiterservice.model.Waiter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WaiterRepository extends MongoRepository<Waiter, String> {

}
