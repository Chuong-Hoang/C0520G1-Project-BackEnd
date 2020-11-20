package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sprint_1.model.MeetingRoom;
import sprint_1.service.MeetingRoomService;

import java.util.List;

@RestController
@CrossOrigin
public class MeetingRoomController {
    @Autowired
    private MeetingRoomService meetingRoomService;

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
}
