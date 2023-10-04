package fact.it.customerservice.repository;

import fact.it.customerservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Customer, String> {
    List<Customer> findByOrderNrIn(List<String> orderNr);
}
