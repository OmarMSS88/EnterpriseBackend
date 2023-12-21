package fact.it.visitservice.repository;

import fact.it.visitservice.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, String> {
}
