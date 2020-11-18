package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.BookedRoom;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
}
