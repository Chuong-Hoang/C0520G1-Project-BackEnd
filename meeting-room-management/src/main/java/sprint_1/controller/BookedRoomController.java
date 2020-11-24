package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.BookedRoomDTO;
import sprint_1.dto.BookedRoomSearchDTO;
import sprint_1.dto.MeetingRoomSearchDTO;
import sprint_1.dto.TestDate;
import sprint_1.model.*;
import sprint_1.service.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BookedRoomController {
    @Autowired
    private BookedRoomService bookedRoomService;

    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private TimeFrameService timeFrameService;

    @Autowired
    private UserService userService;

    @GetMapping("booked-room") // list only
    public ResponseEntity<List<BookedRoomDTO>> showAllBookedRooms() {
        List<BookedRoom> list = bookedRoomService.findAll();
        List<BookedRoomDTO> listDTO = new ArrayList<>();
        BookedRoomDTO bookedRoomDTO = null;
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (BookedRoom room : list){
            bookedRoomDTO = new BookedRoomDTO();
            bookedRoomDTO.setMeetingRoomName(room.getMeetingRoom().getRoomName());
            bookedRoomDTO.setContent(room.getContent());
            bookedRoomDTO.setStartTime(room.getStartTime().getTimeValue());
            bookedRoomDTO.setEndTime(room.getEndTime().getTimeValue());
            bookedRoomDTO.setStartDate(room.getStartDate());
            bookedRoomDTO.setEndDate(room.getEndDate());
            bookedRoomDTO.setRoomType(room.getMeetingRoom().getRoomType().getRoomTypeName());
            bookedRoomDTO.setBookedDate(room.getBookedDate());
            bookedRoomDTO.setBookedStatus(room.getBookedStatus());
            bookedRoomDTO.setBookedUserId(room.getBookedUser().getIdUser());
            listDTO.add(bookedRoomDTO);
        }
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @PostMapping("booked-room/search") // list and search combined
    public ResponseEntity<List<BookedRoom>> showAllBookedRooms(@RequestBody BookedRoomSearchDTO bookedRoomSearchDTO) throws Exception {
        // (*) find all
        List<BookedRoom> list = bookedRoomService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (1) search by roomName
        List<BookedRoom> listNames = new ArrayList<>();
        MeetingRoom meetingRoom = null;
        // a. roomName == '';
        if ("".equals(bookedRoomSearchDTO.getRoomName())) {
            listNames = list;
        } else {
            // b. filter by 'roomName'
            for (BookedRoom room : list){
                meetingRoom = room.getMeetingRoom();
                if (meetingRoom.getRoomName().contains(bookedRoomSearchDTO.getRoomName())){
                    listNames.add(room);
                }
            }
        }
        // c. listName not contain 'roomName'
        if (listNames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (2) search by bookedStatus <=> listNames not empty
        List<BookedRoom> listStatus = new ArrayList<>();
        if ("".equals(bookedRoomSearchDTO.getBookedStatus())){
            listStatus = listNames;
        } else {
            for(BookedRoom room : listNames){
                if(bookedRoomSearchDTO.getBookedStatus().contains(room.getBookedStatus())){
                    listStatus.add(room);
                }
            }
        }

        // (3) search by roomType <=> listStatus never empty.
        List<BookedRoom> listRoomTypes = new ArrayList<>();
        // a. roomType == '';
        if ("".equals(bookedRoomSearchDTO.getRoomType())){
            listRoomTypes = listStatus;
        } else {
            // b.filter by 'roomType' in listStatus
            for(BookedRoom room : listStatus){
                if(room.getMeetingRoom().getRoomType().getRoomTypeName().contains(bookedRoomSearchDTO.getRoomType())){
                    listRoomTypes.add(room);
                }
            }
        }
        // c. listStatus not contain 'roomType'
        if(listRoomTypes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (4) search by startDate <=> listRoomTypes not empty
        List<BookedRoom> listStartDates = new ArrayList<>();
        // a. startDate == '';
        if ("".equals(bookedRoomSearchDTO.getStartDate())){
            listStartDates = listRoomTypes;
        } else {
            // b.filter by 'startDate' in listRoomTypes
            System.err.println("startDate:" + bookedRoomSearchDTO.getStartDate());
            for (BookedRoom room : listRoomTypes){
              if (TestDate.compareDates(room.getStartDate(), bookedRoomSearchDTO.getStartDate())==0){
                  listStartDates.add(room);
              }
            }
        }
        // c. listRoomTypes not contain 'startDate'
        if (listStartDates.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (5) search by endDate <=> listStartDates not empty
        List<BookedRoom> listEndDates = new ArrayList<>();
        // a. endDate == '';
        if ("".equals(bookedRoomSearchDTO.getEndDate())){
            listEndDates = listStartDates;
        } else {
            // b.filter by 'endDate' in listStartDates
            System.err.println("endDate:" + bookedRoomSearchDTO.getEndDate());
            for(BookedRoom room : listStartDates){
                if(TestDate.compareDates(room.getEndDate(), bookedRoomSearchDTO.getEndDate())==0){
                    listEndDates.add(room);
                }
            }
        }
        // c. listStartDates not contain 'startDate'
        if(listEndDates.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (6) search by bookedDate <=> listEndDates not empty
        List<BookedRoom> listBookedDates = new ArrayList<>();
        // a. bookedDate == '';
        if ("".equals(bookedRoomSearchDTO.getBookedDate())){
            listBookedDates = listEndDates;
        } else {
            // b.filter by 'endDate' in listStartDates
            System.err.println("bookedDate:" + bookedRoomSearchDTO.getBookedDate());
            for (BookedRoom room : listEndDates){
                if(TestDate.compareDates(room.getBookedDate(), bookedRoomSearchDTO.getBookedDate())==0){
                    listBookedDates.add(room);
                }
            }
        }
        // c. listEndDates not contain 'bookedDate'
        if(listBookedDates.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listBookedDates, HttpStatus.OK);
    }

    @GetMapping("booked-room/{id}")
    public ResponseEntity<BookedRoom> findBookedRoomById(@PathVariable long id) {
        BookedRoom bookedRoom = bookedRoomService.findById(id);
        if (bookedRoom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookedRoom, HttpStatus.OK);
    }

    @PostMapping("booked-room")
    public ResponseEntity<Void> addNewBookedRoom(@RequestBody BookedRoomDTO bookedRoomDTO){
        BookedRoom bookedRoom = new BookedRoom();
        bookedRoom.setMeetingRoom(meetingRoomService.findById(bookedRoomDTO.getMeetingRoomId()));
        bookedRoom.setBookedUser(userService.findById(bookedRoomDTO.getBookedUserId()));
        bookedRoom.setStartDate(bookedRoomDTO.getStartDate());
        bookedRoom.setEndDate(bookedRoomDTO.getEndDate());
        bookedRoom.setBookedDate(bookedRoomDTO.getBookedDate());
        bookedRoom.setStartTime(timeFrameService.findById(bookedRoomDTO.getStartTimeId()));
        bookedRoom.setEndTime(timeFrameService.findById(bookedRoomDTO.getEndTimeId()));
        bookedRoom.setBookedStatus(bookedRoomDTO.getBookedStatus());
        bookedRoom.setContent(bookedRoomDTO.getContent());
        bookedRoomService.save(bookedRoom);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("meeting-room-list")
    public ResponseEntity<List<MeetingRoomSearchDTO>> showAllMeetingRooms() {
        List<MeetingRoom> list = meetingRoomService.findAll();
        List<MeetingRoomSearchDTO> listDTO = new ArrayList<>();
        List<AssetDetail> assetList = null;
        String assetString = "";
        MeetingRoomSearchDTO meetingRoomDTO = null;
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (MeetingRoom room : list){
            meetingRoomDTO = new MeetingRoomSearchDTO();
            meetingRoomDTO.setRoomName(room.getRoomName());
            meetingRoomDTO.setCapacity(room.getCapacity());
            meetingRoomDTO.setZone(room.getZone());
            assetList = (List<AssetDetail>) room.getAssetDetailCollection();
//            System.err.println("===>assetList: " + assetList.toString());
            for (AssetDetail assetDetail : assetList){
                assetString += assetDetail.getAsset().getAssetName() + ": " +
                        assetDetail.getAssetQuantity() + ". ";
            }
//            System.err.println("asset: -->" + assetString);
            meetingRoomDTO.setRoomAsset(assetString);
            listDTO.add(meetingRoomDTO);
        }
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    @GetMapping("meeting-room/{id}")
    public ResponseEntity<MeetingRoom> findMeetingRoomById(@PathVariable long id) {
        MeetingRoom meetingRoom = meetingRoomService.findById(id);
        if (meetingRoom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meetingRoom, HttpStatus.OK);
    }

    @GetMapping("time-frame")
    public ResponseEntity<List<TimeFrame>> showAllTimeFrames() {
        List<TimeFrame> list = timeFrameService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("room-type")
    public ResponseEntity<List<RoomType>> showAllRoomTypes() {
        List<RoomType> list = roomTypeService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("meeting-room-find") // list and search combined
    public ResponseEntity<List<MeetingRoom>> showMeetingRooms(@RequestBody MeetingRoomSearchDTO meetingRoomSearchDTO) throws Exception {
        // (*) find all
        List<MeetingRoom> list = meetingRoomService.findAll();
        if (list.isEmpty() || meetingRoomSearchDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (1) search by 'zone'
        List<MeetingRoom> listZones = new ArrayList<>();
        // a. zone == '';
        if ("".equals(meetingRoomSearchDTO.getZone())) {
            listZones = list;
        } else {
            // b. filter by 'zone' in list
            for (MeetingRoom room : list){
                if (room.getZone().contains(meetingRoomSearchDTO.getZone())){
                    listZones.add(room);
                }
            }
        }
        // c. listZones not contain any 'zone'
        if (listZones.isEmpty()) {
            System.err.println("List Zone is empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (2) search by roomType <=> listZones never empty.
        List<MeetingRoom> listRoomTypes = new ArrayList<>();
        // a. roomTypeName == '';
        if ("".equals(meetingRoomSearchDTO.getRoomTypeName())){
            listRoomTypes = listZones;
        } else {
            // b.filter by 'roomTypeName' in listZones
            for (MeetingRoom room : listZones){
                if (room.getRoomType().getRoomTypeName().contains(meetingRoomSearchDTO.getRoomTypeName())){
                    listRoomTypes.add(room);
                }
            }
        }
        // c. listRoomTypes not contain 'roomTypeName'
        if (listRoomTypes.isEmpty()){
            System.err.println("listRoomTypes is empty...");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (3) search by 'capacity'
        List<MeetingRoom> listCapacity = new ArrayList<>();

        // a. capacity == ''
        if ("".equals(meetingRoomSearchDTO.getCapacity())){
            listCapacity = listRoomTypes;
        } else {
            // b.filter by 'capacity' in listRoomTypes
            for (MeetingRoom room : listRoomTypes){
                if (Integer.parseInt(room.getCapacity()) >= Integer.parseInt(meetingRoomSearchDTO.getCapacity())){
                    listCapacity.add(room);
                }
            }
        }
        // c.listRoomTypes not contain any 'capacity'
        if (listCapacity.isEmpty()){
            System.err.println("listCapacity is empty...");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (4) search by startDate && endDate && startTime && endTime <=> listCapacity not empty
        List<MeetingRoom> listDateAndTimes = new ArrayList<>();
        List<BookedRoom> bookedRoomList = new ArrayList<>();
        String startDateInput = meetingRoomSearchDTO.getStartDate();
        String endDateInput = meetingRoomSearchDTO.getEndDate();
        String startTimeInput = meetingRoomSearchDTO.getStartTime();
        String endTimeInput = meetingRoomSearchDTO.getEndTime();
        System.err.println("startDateInput: " + startDateInput);
        System.err.println("endDateInput: " + endDateInput);
        System.err.println("startTimeInput: " + startTimeInput);
        System.err.println("endTimeInput: " + endTimeInput);
        boolean isAvailableRoom = true;
        for (MeetingRoom meetingRoom : listCapacity){
            bookedRoomList = (List<BookedRoom>) meetingRoom.getBookedRoomCollection();
            for (BookedRoom bookedRoom : bookedRoomList){
                if((TestDate.compareDates(bookedRoom.getStartDate(), startDateInput) <= 0)
                    && (TestDate.compareDates(bookedRoom.getEndDate(), endDateInput) <= 0)
                    && (Long.parseLong(startTimeInput) >= bookedRoom.getStartTime().getIdTime())
                    && (Long.parseLong(endTimeInput) <= bookedRoom.getEndTime().getIdTime())){
                    isAvailableRoom = false;
                    break;
                }
            }
            if(isAvailableRoom && meetingRoom.getRoomStatus().getIdRoomStatus() == 2){
                listDateAndTimes.add(meetingRoom);
            }
        }

        if(listDateAndTimes.isEmpty()){
            System.out.println("listDateAndTimes: is empty...");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listDateAndTimes, HttpStatus.OK);
    }
}
