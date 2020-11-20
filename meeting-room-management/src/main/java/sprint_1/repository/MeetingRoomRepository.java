package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.MeetingRoom;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom,Long> {
}
