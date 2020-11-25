package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.BookedRoom;

import java.util.List;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
    /**
     * find BookedRoom by room name
     * create Nguyen Tien Hai
     *
     * @return list BookedRoom
     */
    List<BookedRoom> findAllByMeetingRoom_RoomName(String roomName);
}
