package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.ErrorType;

public interface ErrorTypeRepository extends JpaRepository<ErrorType,Long> {
}
