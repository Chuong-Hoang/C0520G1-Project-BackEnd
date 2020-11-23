package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.MeetingRoomDTO;
import sprint_1.model.MeetingRoom;
import sprint_1.model.RoomStatus;
import sprint_1.model.RoomType;
import sprint_1.service.MeetingRoomService;
import sprint_1.service.RoomStatusService;
import sprint_1.service.RoomTypeService;

import java.util.List;

@RestController
@CrossOrigin
public class MeetingRoomController {
    @Autowired
    private MeetingRoomService meetingRoomService;
    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    RoomStatusService roomStatusService;


    @GetMapping("meeting-room")
    public ResponseEntity<List<MeetingRoom>> showAll() {
        List<MeetingRoom> list = meetingRoomService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<MeetingRoom> findById(@PathVariable long id) {
        MeetingRoom meetingRoom = meetingRoomService.findById(id);
        if (meetingRoom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meetingRoom, HttpStatus.OK);
    }

    @GetMapping("/getRoomTypeList")
    public ResponseEntity<List<RoomType>> listRoomType() {
        List<RoomType> roomTypes = roomTypeService.findAll();
        if (roomTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomTypes, HttpStatus.OK);
    }

    @GetMapping("/getRoomStatusList")
    public ResponseEntity<List<RoomStatus>> listRoomStatus() {
        List<RoomStatus> roomStatuses = roomStatusService.findAll();
        if (roomStatuses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomStatuses, HttpStatus.OK);
    }

    @PostMapping("/createMeetingRoom")
    public ResponseEntity<Void> add(@RequestBody MeetingRoomDTO meetingRoomDTO){
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setRoomName(meetingRoomDTO.getRoomName());
        meetingRoom.setFloor(meetingRoomDTO.getFloor());
        meetingRoom.setZone(meetingRoomDTO.getZone());
        meetingRoom.setCapacity(meetingRoomDTO.getCapacity());
        meetingRoom.setImage(meetingRoomDTO.getImage());
        meetingRoom.setStartDate(meetingRoomDTO.getStartDate());
        meetingRoom.setEndDate(meetingRoomDTO.getEndDate());
        meetingRoom.setRoomType(roomTypeService.findById(meetingRoomDTO.getRoomTypeId()));
        meetingRoom.setRoomStatus(roomStatusService.findById(meetingRoomDTO.getRoomStatusId()));
        meetingRoomService.save(meetingRoom);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
