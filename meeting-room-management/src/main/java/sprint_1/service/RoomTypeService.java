package sprint_1.service;

import sprint_1.model.MeetingRoom;
import sprint_1.model.RoomType;

import java.util.List;

public interface RoomTypeService {
    List<RoomType> findAll();

    void save(RoomType roomType);

    RoomType findById(Long id);

    void remove(Long id);

}
