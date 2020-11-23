package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sprint_1.model.RoomStatus;
import sprint_1.model.RoomType;
import sprint_1.model.TimeFrame;
import sprint_1.service.RoomStatusService;
import sprint_1.service.RoomTypeService;
import sprint_1.service.TimeFrameService;

import java.util.List;

@RestController
@CrossOrigin
public class CommonController {
    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomStatusService roomStatusService;


    @Autowired
    private TimeFrameService timeFrameService;
    @GetMapping("room-type")

    public ResponseEntity<List<RoomType>> showAllRoomType() {
        List<RoomType> list = roomTypeService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("room-status")
    public ResponseEntity<List<RoomStatus>> showAllRoomStatus() {
        List<RoomStatus> list = roomStatusService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("time-frame")
    public ResponseEntity<List<TimeFrame>> showAllTimeFrames() {
        List<TimeFrame> list = timeFrameService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
