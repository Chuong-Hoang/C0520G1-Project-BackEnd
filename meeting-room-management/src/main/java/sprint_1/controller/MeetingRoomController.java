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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("meeting-room")

/**
 * EmploymentDetailsDAO
 *
 * Version 1.0
 *
 * Date: 25-11-2020
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 25-11-2020        Quang|Trà            Create
 */
public class MeetingRoomController {
    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomStatusService roomStatusService;


    /**
     * Quang
     * get all column name record from table room-type
     * get data for meeting-room page
     * @return list<String>
     * @throws
     */
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

    /**
     * Quang
     * get all column name record from table room-status
     * get data for meeting-room page
     *
     * @return list<String>
     * @throws
     */
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

    /**
     * Quang
     * get all column name record from table
     * get data for meeting-room page
     * @return list<MeetingRoomDTO>
     * @throws
     */
    @GetMapping("")
    public ResponseEntity<List<MeetingRoomDTO>> showAllMeetingRoom() {
        List<MeetingRoom> list = meetingRoomService.findAll();

        List<MeetingRoomDTO> listDTO = new ArrayList<>();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (MeetingRoom meetingRoom : list) {
            listDTO.add(new MeetingRoomDTO(meetingRoom.getIdRoom(),
                    meetingRoom.getRoomName(),
                    meetingRoom.getFloor(),
                    meetingRoom.getZone(),
                    meetingRoom.getCapacity(),
                    meetingRoom.getImage(),
                    meetingRoom.getStartDate(),
                    meetingRoom.getEndDate(),
                    meetingRoom.getRoomType().getRoomTypeName(),
                    meetingRoom.getRoomStatus().getRoomStatusName()));
        }
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    /**
     * Quang
     * find meeting-room record from table
     * @param id
     * @return Object MeetingRoomDTO
     * @throws
     */
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

    /**
     * Quang
     * find meeting-room record correspond with date input from table
     * @param meetingRoomSearchDTOD
     * @return List<MeetingRoomDTO>
     * @throws
     */
    @PostMapping("/search")
    public ResponseEntity<List<MeetingRoomDTO>> searchMeetingRoom(@RequestBody MeetingRoomSearchDTO meetingRoomSearchDTOD) {
        String roomNameSearch = meetingRoomSearchDTOD.getRoomName();
        String floorSearch = meetingRoomSearchDTOD.getFloor();
        String zoneSearch = meetingRoomSearchDTOD.getZone();
        String roomStatusSearch = meetingRoomSearchDTOD.getRoomStatusName();
        String roomTypeSearch = meetingRoomSearchDTOD.getRoomTypeName();
        String capacitySearch = meetingRoomSearchDTOD.getCapacity();

        List<MeetingRoom> list = meetingRoomService.searchAllFields(roomNameSearch, capacitySearch, zoneSearch, floorSearch, roomStatusSearch, roomTypeSearch);
        if (list == null) {
            list = new ArrayList<>();
        }

        List<MeetingRoomDTO> listSearch;
        listSearch = new ArrayList<>();
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
    }

    /**
     * Quang
     * find meeting-room record correspond with date input from table
     * @param id
     * @return List<MeetingRoomDTO>
     * @throws
     */
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
    public ResponseEntity<Void> add(@Valid @RequestBody MeetingRoomDTO meetingRoomDTO){
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setRoomName(meetingRoomDTO.getRoomName());
        meetingRoom.setFloor(meetingRoomDTO.getFloor());
        meetingRoom.setZone(meetingRoomDTO.getZone());
        meetingRoom.setCapacity(meetingRoomDTO.getCapacity());
        meetingRoom.setImage(meetingRoomDTO.getImage());
        meetingRoom.setStartDate(meetingRoomDTO.getStartDate());
        meetingRoom.setEndDate(meetingRoomDTO.getEndDate());
        meetingRoom.setRoomType(roomTypeService.findByName(meetingRoomDTO.getRoomTypeName()));
        meetingRoom.setRoomStatus(roomStatusService.findById(1L));
        meetingRoom.setDeleteStatus(true);
        meetingRoomService.save(meetingRoom);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MeetingRoomDTO> findMeetingById(@PathVariable long id) {
        MeetingRoom meetingRoom = meetingRoomService.findById(id);
        if (meetingRoom == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        MeetingRoomDTO meetingRoomDTO = new MeetingRoomDTO(
                meetingRoom.getIdRoom(),
                meetingRoom.getRoomName(),
                meetingRoom.getFloor(),
                meetingRoom.getZone(),
                meetingRoom.getCapacity(),
                meetingRoom.getImage(),
                meetingRoom.getStartDate(),
                meetingRoom.getEndDate(),
                meetingRoom.getRoomType().getRoomTypeName(),
                meetingRoom.getRoomStatus().getRoomStatusName()
        );
        return new ResponseEntity<>(meetingRoomDTO, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> editMeetingRoom(@Valid @RequestBody MeetingRoomDTO meetingRoomDTO, @PathVariable Long id){
        MeetingRoom meetingRoom = meetingRoomService.findById(id);
        if(meetingRoom == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        meetingRoom.setRoomName(meetingRoomDTO.getRoomName());
        meetingRoom.setFloor(meetingRoomDTO.getFloor());
        meetingRoom.setZone(meetingRoomDTO.getZone());
        meetingRoom.setCapacity(meetingRoomDTO.getCapacity());
        meetingRoom.setImage(meetingRoomDTO.getImage());
        meetingRoom.setStartDate(meetingRoomDTO.getStartDate());
        meetingRoom.setEndDate(meetingRoomDTO.getEndDate());
        meetingRoom.setRoomType(roomTypeService.findByName(meetingRoomDTO.getRoomTypeName()));
        meetingRoom.setRoomStatus(roomStatusService.findById(1L));
        meetingRoom.setDeleteStatus(true);
        meetingRoomService.save(meetingRoom);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}