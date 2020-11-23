package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sprint_1.model.MeetingRoom;
import sprint_1.model.RoomType;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    List<MeetingRoom> findAllByRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContaining
            (String nameRoom, String capacity, String floor, String zone);

    List<MeetingRoom> findAllByRoomNameContaining(String nameRoom);

    MeetingRoom findByRoomName (String name);
}
