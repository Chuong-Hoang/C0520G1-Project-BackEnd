package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.TimeFrame;

public interface TimeFrameRepository extends JpaRepository<TimeFrame,Long> {
}
