package fact.it.tableservice.repository;

import fact.it.tableservice.model.Table;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TableRepository extends MongoRepository<Table, String> {
    List<Table> findByTableNrIn(List<String> tableNr);
}
