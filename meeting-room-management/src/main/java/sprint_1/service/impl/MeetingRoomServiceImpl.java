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
        return meetingRoomRepository.findAllByDeleteStatusTrue();
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

    @Override
    public List<MeetingRoom> searchAllFields(String nameRoom, String zone, String floor, String status, String type) {
        if (status.equals("") && !type.isEmpty()) {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndZoneContainingAndFloorContainingAndRoomType_RoomTypeName(nameRoom, zone, floor, type);
        } else if (type.equals("") && !status.isEmpty()) {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndZoneContainingAndFloorContainingAndRoomStatus_RoomStatusName(nameRoom,  zone, floor, status);
        } else if (type.equals("") && status.equals("")) {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndZoneContainingAndFloorContaining(nameRoom, zone, floor);
        } else {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndZoneContainingAndFloorContainingAndRoomStatus_RoomStatusNameAndRoomType_RoomTypeName(nameRoom, zone, floor, status, type);
        }
    }
}
