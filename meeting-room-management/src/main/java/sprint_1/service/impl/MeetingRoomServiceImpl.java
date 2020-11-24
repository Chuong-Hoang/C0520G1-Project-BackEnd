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
    public List<MeetingRoom> searchAllFields(String nameRoom, String capacity, String floor, String zone, String status, String type) {
        if (status.equals("") && !type.isEmpty()) {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContainingAndRoomStatus_RoomStatusName(nameRoom, capacity, floor, zone, status);
        } else if (type.equals("") && !status.isEmpty()) {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContainingAndRoomType_RoomTypeName(nameRoom, capacity, floor, zone, type);
        } else if (type.equals("") && status.equals("")) {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContaining(nameRoom, capacity, floor, zone);
        } else {
            return meetingRoomRepository.findAllByDeleteStatusTrueAndRoomNameContainingAndCapacityContainingAndZoneContainingAndFloorContainingAndRoomStatus_RoomStatusNameAndRoomType_RoomTypeName(nameRoom, capacity, floor, zone, status, type);
        }
    }
}
