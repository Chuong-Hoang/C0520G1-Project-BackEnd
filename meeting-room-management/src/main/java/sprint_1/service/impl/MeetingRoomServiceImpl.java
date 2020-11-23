package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.MeetingRoom;
import sprint_1.repository.MeetingRoomRepository;
import sprint_1.service.MeetingRoomService;

import java.util.List;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {
    @Autowired
    MeetingRoomRepository meetingRoomRepository;

    @Override
    public MeetingRoom findByRoomName(String name) {
        return meetingRoomRepository.findByRoomName(name);
    }

    @Override
    public List<MeetingRoom> findAll() {
        return meetingRoomRepository.findAll();
    }

    @Override
    public MeetingRoom findById(Long id) {
        return meetingRoomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(MeetingRoom meetingRoom) {
        meetingRoomRepository.save(meetingRoom);
    }

    @Override
    public void delete(Long id) {
        meetingRoomRepository.deleteById(id);
    }

    //    @Override
//    public List<MeetingRoom> searchAllFields(String nameRoom, String capacity, String floor, String status, String type, String zone) {
//        return meetingRoomRepository.findAllByRoomNameAndCapacityAndFloorAndRoomStatus_RoomStatusNameAndRoomType_RoomTypeNameAndZoneContaining(nameRoom, capacity, floor, status, type, zone);
//    }
    @Override
    public List<MeetingRoom> searchAllFields(String nameRoom, String capacity, String floor, String zone) {
//    return meetingRoomRepository.findAllByRoomNameAndCapacityAndFloorAndRoomStatus_RoomStatusNameAndRoomType_RoomTypeNameAndZoneContaining(nameRoom, capacity, floor, status, type, zone);
        return meetingRoomRepository.findAllByRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContaining(nameRoom, capacity, floor, zone);
    }

    @Override
    public List<MeetingRoom> searchByName(String roomName) {
        return meetingRoomRepository.findAllByRoomNameContaining(roomName);
    }
}
