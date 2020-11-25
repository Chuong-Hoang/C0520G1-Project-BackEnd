package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sprint_1.model.MeetingRoom;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    List<MeetingRoom> findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContainingAndRoomStatus_RoomStatusNameAndRoomType_RoomTypeName
            (String nameRoom, String capacity, String floor, String zone, String status, String type);

    List<MeetingRoom> findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContaining
            (String nameRoom, String capacity, String floor, String zone);

    List<MeetingRoom> findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContainingAndRoomStatus_RoomStatusName
            (String nameRoom, String capacity, String floor, String zone, String status);

    List<MeetingRoom> findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContainingAndRoomType_RoomTypeName
            (String nameRoom, String capacity, String floor, String zone, String type);

    List<MeetingRoom> findAllByDeleteStatusTrue();

    MeetingRoom findByRoomName(String name);
}
