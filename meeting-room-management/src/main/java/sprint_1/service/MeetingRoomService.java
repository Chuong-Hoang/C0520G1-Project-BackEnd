package sprint_1.service;


import sprint_1.model.MeetingRoom;

import java.util.List;

public interface MeetingRoomService {

    List<MeetingRoom> findAll();

    MeetingRoom findById(Long id);

    void save(MeetingRoom meetingRoom);

    void delete(Long id);

    List<MeetingRoom> searchAllFields(String nameRoom, String capacity, String floor, String zone);

    List<MeetingRoom> searchByName(String roomName);
}
