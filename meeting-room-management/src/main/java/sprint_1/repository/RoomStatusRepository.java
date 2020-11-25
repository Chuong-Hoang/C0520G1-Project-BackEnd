package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.RoomStatus;

public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
    RoomStatus findRoomStatusByRoomStatusName(String name);
}
