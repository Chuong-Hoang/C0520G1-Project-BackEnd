package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.AssetDetailDTO;
import sprint_1.dto.MeetingRoomDTO;
import sprint_1.dto.MeetingRoomSearchDTO;
import sprint_1.model.AssetDetail;
import sprint_1.model.MeetingRoom;
import sprint_1.model.RoomStatus;
import sprint_1.model.RoomType;
import sprint_1.service.MeetingRoomService;
import sprint_1.service.RoomStatusService;
import sprint_1.service.RoomTypeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("meeting-room")
public class MeetingRoomController {
    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomStatusService roomStatusService;

    @GetMapping("room-type-list")
    public ResponseEntity<List<String>> showAllRoomType() {
        List<RoomType> list = roomTypeService.findAll();

        List<String> listRoomType = new ArrayList<>();
        for (RoomType roomType : list) {
            listRoomType.add(roomType.getRoomTypeName());
        }
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listRoomType, HttpStatus.OK);
    }

    @GetMapping("room-status-list")
    public ResponseEntity<List<String>> showAllRoomStatus() {
        List<RoomStatus> list = roomStatusService.findAll();

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<String> listRoomType = new ArrayList<>();
        for (RoomStatus roomStatus : list) {
            listRoomType.add(roomStatus.getRoomStatusName());
        }
        return new ResponseEntity<>(listRoomType, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<MeetingRoomDTO>> showAll() {
        List<MeetingRoom> list = meetingRoomService.findAll();

        List<MeetingRoomDTO> listDto = new ArrayList<>();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (MeetingRoom el : list) {
            listDto.add(new MeetingRoomDTO(el.getIdRoom(),
                    el.getRoomName(),
                    el.getFloor(),
                    el.getZone(),
                    el.getCapacity(),
                    el.getImage(),
                    el.getStartDate(),
                    el.getEndDate(),
                    el.getRoomType().getRoomTypeName(),
                    el.getRoomStatus().getRoomStatusName()));
        }
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingRoomDTO> findById(@PathVariable long id) {
        MeetingRoom el = meetingRoomService.findById(id);
        List<AssetDetail> assetDetails = (List<AssetDetail>) el.getAssetDetailCollection();
        List<AssetDetailDTO> assetDetailsDTO = new ArrayList<>();

        if (el == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (AssetDetail elAsset : assetDetails) {
            assetDetailsDTO.add(new AssetDetailDTO(elAsset.getAsset().getAssetName(), elAsset.getAssetQuantity()));
        }
        MeetingRoomDTO meetingRoomDto = new MeetingRoomDTO(el.getIdRoom(),
                el.getRoomName(),
                el.getFloor(),
                el.getZone(),
                el.getCapacity(),
                el.getImage(),
                el.getStartDate(),
                el.getEndDate(),
                el.getRoomType().getRoomTypeName(),
                el.getRoomStatus().getRoomStatusName(),
                assetDetailsDTO);

        return new ResponseEntity<>(meetingRoomDto, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<MeetingRoomDTO>> searchMeetingRoom(@RequestBody MeetingRoomSearchDTO meetingRoomSearchDTOD) {

        String roomName = meetingRoomSearchDTOD.getRoomName();
        String floor = meetingRoomSearchDTOD.getFloor();
        String zone = meetingRoomSearchDTOD.getZone();
        String roomStatus = meetingRoomSearchDTOD.getRoomStatusName();
        String roomType = meetingRoomSearchDTOD.getRoomTypeName();
        String capacity = meetingRoomSearchDTOD.getCapacity();

        List<MeetingRoom> list = meetingRoomService.searchAllFields(roomName, capacity, zone, floor, roomStatus, roomType);
//
        if (list == null) {
            list = new ArrayList<>();
        }

        List<MeetingRoomDTO> listSearch; listSearch = new ArrayList<>();
        for (MeetingRoom el : list) {
            listSearch.add(new MeetingRoomDTO(el.getIdRoom(),
                    el.getRoomName(),
                    el.getFloor(),
                    el.getZone(),
                    el.getCapacity(),
                    el.getImage(),
                    el.getStartDate(),
                    el.getEndDate(),
                    el.getRoomType().getRoomTypeName(),
                    el.getRoomStatus().getRoomStatusName()));
        }
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listSearch, HttpStatus.OK);
//        }
    }

    ;

    @PutMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        MeetingRoom meetingRoom = meetingRoomService.findById(id);
        if (meetingRoom == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        meetingRoom.setDeleteStatus(false);
        meetingRoomService.save(meetingRoom);
        return new ResponseEntity<>(HttpStatus.OK);
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
        meetingRoom.setRoomType(roomTypeService.findByName(meetingRoomDTO.getRoomTypeName()));
        meetingRoom.setRoomStatus(roomStatusService.findByName(meetingRoomDTO.getRoomStatusName()));
        meetingRoomService.save(meetingRoom);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}