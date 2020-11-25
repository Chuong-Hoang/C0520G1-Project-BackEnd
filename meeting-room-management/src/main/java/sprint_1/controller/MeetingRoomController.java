package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.AssetDetailDTO;
import sprint_1.dto.MeetingRoomDto;
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

    @GetMapping("")
    public ResponseEntity<List<MeetingRoomDto>> showAll() {
        List<MeetingRoom> list = meetingRoomService.findAll();
        List<MeetingRoomDto> listDto = new ArrayList<>();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (MeetingRoom el : list) {
            listDto.add(new MeetingRoomDto(el.getIdRoom(),
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
    public ResponseEntity<MeetingRoomDto> findById(@PathVariable long id) {
        MeetingRoom el = meetingRoomService.findById(id);
        List<AssetDetail> assetDetails = (List<AssetDetail>) el.getAssetDetailCollection();
        List<AssetDetailDTO> assetDetailsDTO = new ArrayList<>();

        if (el == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (AssetDetail elAsset : assetDetails) {
            assetDetailsDTO.add(new AssetDetailDTO(elAsset.getAsset().getAssetName(), elAsset.getAssetQuantity()));
        }
        MeetingRoomDto meetingRoomDto = new MeetingRoomDto(el.getIdRoom(),
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
    public ResponseEntity<List<MeetingRoomDto>> searchMeetingRoom(@RequestBody MeetingRoomSearchDTO meetingRoomSearchDTOD) {
        String roomName = meetingRoomSearchDTOD.getRoomName();
        String floor = meetingRoomSearchDTOD.getFloor();
        String zone = meetingRoomSearchDTOD.getZone();
//        String roomStatus = meetingRoomDto.getRoomStatusName();
//        String roomType = meetingRoomDto.getRoomTypeName();
        String capacity = meetingRoomSearchDTOD.getCapacity();
//        List<MeetingRoom> list = meetingRoomService.searchAllFields(roomName, capacity, floor, roomStatus, roomType, zone);
        List<MeetingRoom> list = meetingRoomService.searchAllFields(roomName, capacity, zone, floor);
//
        if (list == null) {
            list = new ArrayList<>();
        }
        List<MeetingRoomDto> listSearch = new ArrayList<>();
        for (MeetingRoom el : list) {
            listSearch.add(new MeetingRoomDto(el.getIdRoom(),
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
    }
}
