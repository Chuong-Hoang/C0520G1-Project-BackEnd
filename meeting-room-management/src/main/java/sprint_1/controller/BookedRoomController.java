package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sprint_1.dto.BookedRoomDTO;
import sprint_1.dto.BookedRoomSearchDTO;
import sprint_1.dto.MeetingRoomSearchDTO;
import sprint_1.dto.AssetDTO;
import sprint_1.dto.TestDate;
import sprint_1.model.*;
import sprint_1.service.*;

/**
 * BookedRoomController
 *
 * Version 1.0
 *
 * Date: 24-11-2020
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * ----------------------------------------------------------
 * 24-11-2020        ChuongHKV           Create/Read/List
 */
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

    @Autowired
    private AssetService assetService;

    // get bookedRoomDTO list from database to show on Frontend
    @GetMapping("booked-room-list/{idUser}")
    public ResponseEntity<List<BookedRoomDTO>> showAllBookedRooms(@PathVariable Long idUser) throws Exception {
        List<BookedRoom> list = bookedRoomService.findAll();

        // check bookedStatus if overdue
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        System.err.println("Today: " + today);
        for(BookedRoom bRom : list){
            if ((TestDate.compareDates(today, bRom.getEndDate()) >= 0) &&
                    (TestDate.getDiffTime(bRom.getEndDate(), bRom.getEndTime().getIdTime()) >= 0) &&
                    ("Đang sử dụng").equals(bRom.getBookedStatus())) {
//                System.err.println("Set bookedStatus for booked-room: id=" + bRom.getIdBookedRoom());
                bRom.setBookedStatus("Đã kết thúc");
            }
            bookedRoomService.save(bRom);
        }

        List<BookedRoomDTO> listDTO = new ArrayList<>();
        BookedRoomDTO bookedRoomDTO = null;
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (BookedRoom room : list){
            if(room.getBookedUser().getIdUser() == idUser) {
                bookedRoomDTO = new BookedRoomDTO();
                bookedRoomDTO.setIdBookedRoom(room.getIdBookedRoom());
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
        }
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    // find booked-rooms based on input fields (search booked-rooms)
    @PostMapping("booked-room/search") // list and search combined
    public ResponseEntity<List<BookedRoomDTO>> showAllBookedRooms(@RequestBody BookedRoomDTO bookedRoomDTO) throws Exception {
        // (*) find all
        List<BookedRoom> list = bookedRoomService.findAll();
        if (list.isEmpty() || bookedRoomDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (1) search by roomName
        List<BookedRoom> listNames = new ArrayList<>();
        MeetingRoom meetingRoom = null;

        // a. roomName == '';
        if ("".equals(bookedRoomDTO.getMeetingRoomName()) || bookedRoomDTO.getMeetingRoomName() == null) {
            listNames = list;
        } else {
            // b. filter by 'roomName'
            for (BookedRoom room : list){
                meetingRoom = room.getMeetingRoom();
                if (meetingRoom.getRoomName().equals(bookedRoomDTO.getMeetingRoomName())){
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
        if ("".equals(bookedRoomDTO.getBookedStatus()) || bookedRoomDTO.getBookedStatus() == null){
            listStatus = listNames;
        } else {
            for(BookedRoom room : listNames){
                if(bookedRoomDTO.getBookedStatus().contains(room.getBookedStatus())){
                    listStatus.add(room);
                }
            }
        }

        // (3) search by roomType <=> listStatus never empty.
        List<BookedRoom> listRoomTypes = new ArrayList<>();

        // a. roomType == '';
        if ("".equals(bookedRoomDTO.getRoomType()) || bookedRoomDTO.getRoomType() == null){
            listRoomTypes = listStatus;
        } else {
            // b.filter by 'roomType' in listStatus
            for(BookedRoom room : listStatus){
                if(room.getMeetingRoom().getRoomType().getRoomTypeName().contains(bookedRoomDTO.getRoomType())){
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
        if ("".equals(bookedRoomDTO.getStartDate()) || bookedRoomDTO.getStartDate() == null){
            listStartDates = listRoomTypes;
        } else {
            // b.filter by 'startDate' in listRoomTypes
            System.err.println("startDate:" + bookedRoomDTO.getStartDate());
            for (BookedRoom room : listRoomTypes){
              if (TestDate.compareDates(room.getStartDate(), bookedRoomDTO.getStartDate())==0){
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
        if ("".equals(bookedRoomDTO.getEndDate()) || bookedRoomDTO.getEndDate() == null){
            listEndDates = listStartDates;
        } else {
            // b.filter by 'endDate' in listStartDates
            System.err.println("endDate:" + bookedRoomDTO.getEndDate());
            for(BookedRoom room : listStartDates){
                if(TestDate.compareDates(room.getEndDate(), bookedRoomDTO.getEndDate())==0){
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
        if ("".equals(bookedRoomDTO.getBookedDate()) || bookedRoomDTO.getBookedDate() == null){
            listBookedDates = listEndDates;
        } else {
            // b.filter by 'endDate' in listStartDates
            System.err.println("bookedDate:" + bookedRoomDTO.getBookedDate());
            for (BookedRoom room : listEndDates){
                if(TestDate.compareDates(room.getBookedDate(), bookedRoomDTO.getBookedDate())==0){
                    listBookedDates.add(room);
                }
            }
        }

        // c. listEndDates not contain 'bookedDate'
        if(listBookedDates.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // change from bookedRoom --> bookRoomDTO
        List<BookedRoomDTO> listDTO = new ArrayList<>();
        BookedRoomDTO bookedRoomDTONew = null;
        for (BookedRoom room : listBookedDates){
            if(room.getBookedUser().getIdUser().equals(bookedRoomDTO.getBookedUserId())) {
                bookedRoomDTONew = new BookedRoomDTO();
                bookedRoomDTONew.setIdBookedRoom(room.getIdBookedRoom());
                bookedRoomDTONew.setMeetingRoomName(room.getMeetingRoom().getRoomName());
                bookedRoomDTONew.setContent(room.getContent());
                bookedRoomDTONew.setStartTime(room.getStartTime().getTimeValue());
                bookedRoomDTONew.setEndTime(room.getEndTime().getTimeValue());
                bookedRoomDTONew.setStartDate(room.getStartDate());
                bookedRoomDTONew.setEndDate(room.getEndDate());
                bookedRoomDTONew.setRoomType(room.getMeetingRoom().getRoomType().getRoomTypeName());
                bookedRoomDTONew.setBookedDate(room.getBookedDate());
                bookedRoomDTONew.setBookedStatus(room.getBookedStatus());
                bookedRoomDTONew.setBookedUserId(room.getBookedUser().getIdUser());
                listDTO.add(bookedRoomDTONew);
            }
        }

        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }

    // find bookedRoom each by Id
    @GetMapping("booked-room/{id}")
    public ResponseEntity<BookedRoom> findBookedRoomById(@PathVariable long id) {
        BookedRoom bookedRoom = bookedRoomService.findById(id);
        if (bookedRoom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookedRoom, HttpStatus.OK);
    }

    // book new meeting-room (bookedRoom)
    @PostMapping("booked-room")
    public ResponseEntity<Void> addNewBookedRoom(@RequestBody BookedRoomDTO bookedRoomDTO) throws ParseException {
        BookedRoom bookedRoom = new BookedRoom();
        if(bookedRoomDTO != null) {
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            bookedRoom.setMeetingRoom(meetingRoomService.findById(bookedRoomDTO.getMeetingRoomId()));
            bookedRoom.setBookedUser(userService.findById(bookedRoomDTO.getBookedUserId())); //.............
            bookedRoom.setStartDate(bookedRoomDTO.getStartDate()); //................
            bookedRoom.setEndDate(bookedRoomDTO.getEndDate()); //....................
            bookedRoom.setBookedDate(bookedRoomDTO.getBookedDate());
            bookedRoom.setStartTime(timeFrameService.findById(bookedRoomDTO.getStartTimeId()));
            bookedRoom.setEndTime(timeFrameService.findById(bookedRoomDTO.getEndTimeId()));
            bookedRoom.setBookedStatus(bookedRoomDTO.getBookedStatus());
            bookedRoom.setContent(bookedRoomDTO.getContent());
            bookedRoomService.save(bookedRoom);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    // get TimeFrame list
//    @GetMapping("time-frame")
//    public ResponseEntity<List<TimeFrame>> showAllTimeFrames() {
//        List<TimeFrame> list = timeFrameService.findAll();
//        if (list.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }

    // find available meeting-rooms with input fields (search meeting-rooms)
    @PostMapping("meeting-room-find") // list and search input fields combined
    public ResponseEntity<List<MeetingRoomSearchDTO>> showMeetingRooms(@RequestBody MeetingRoomSearchDTO meetingRoomSearchDTO) throws Exception {

        // (*) find all
        List<MeetingRoom> list = meetingRoomService.findAll();
        if (list.isEmpty() || meetingRoomSearchDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (1) search by 'zone'
        List<MeetingRoom> listZones = new ArrayList<>();

        // a. zone == '';
        if ("".equals(meetingRoomSearchDTO.getZone()) || meetingRoomSearchDTO.getZone() == null) {
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (2) search by roomType <=> listZones never empty.
        List<MeetingRoom> listRoomTypes = new ArrayList<>();

        // a. roomTypeName == '';
        if ("".equals(meetingRoomSearchDTO.getRoomTypeName()) || meetingRoomSearchDTO.getRoomTypeName() == null){
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (3) search by 'capacity'
        List<MeetingRoom> listCapacity = new ArrayList<>();

        // a. capacity == ''
        if ("".equals(meetingRoomSearchDTO.getCapacity()) || meetingRoomSearchDTO.getCapacity() == null){
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // (4) search by startDate && endDate && startTime && endTime <=> listCapacity not empty
        List<MeetingRoom> listDateAndTimes = new ArrayList<>();
        List<BookedRoom> bookedRoomList = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d1Input = meetingRoomSearchDTO.getStartDate();
        String d2Input = meetingRoomSearchDTO.getEndDate();
        long t1Input = Long.parseLong(meetingRoomSearchDTO.getStartTime());
        long t2Input = Long.parseLong(meetingRoomSearchDTO.getEndTime());
        boolean isAvailableRoom = true;
        System.err.println("Search start-date=> " + sdf.format(sdf.parse(d1Input)));
        System.err.println("Search end-date=> " + sdf.format(sdf.parse(d2Input)));
        for (MeetingRoom meetingRoom : listCapacity){
            bookedRoomList = (List<BookedRoom>) meetingRoom.getBookedRoomCollection();
            if (!bookedRoomList.isEmpty()){
                isAvailableRoom = true;
                for (BookedRoom bookedRoom : bookedRoomList) {
                    System.err.println("Room:" + bookedRoom.getMeetingRoom().getRoomName() + "=>startDate:" + bookedRoom.getStartDate());
                    System.err.println("Room:" + bookedRoom.getMeetingRoom().getRoomName() + "=>endDate:" + bookedRoom.getEndDate());

                    // case 1. t2>T1 && t2<=T2 && d1>=D1 && d1<=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d1Input) <= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d1Input) >= 0) &&
                            (t2Input > bookedRoom.getStartTime().getIdTime() &&
                                    (t2Input <= bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 2. t1>=T1 && t1<T2 && d1>=D1 && d1<=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d1Input) <= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d1Input) >= 0) &&
                            (t1Input >= bookedRoom.getStartTime().getIdTime() &&
                                    (t1Input < bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 3. t2>T1 && t2<=T2 && d2>=D1 && d2<=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d2Input) <= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d2Input) >= 0) &&
                            (t2Input > bookedRoom.getStartTime().getIdTime() &&
                                    (t2Input <= bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 4. t1>=T1 && t1<T2 && d2>=D1 && d2<=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d2Input) <= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d2Input) >= 0) &&
                            (t1Input >= bookedRoom.getStartTime().getIdTime() &&
                                    (t1Input < bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 5. t1<=T1 && t2>=T2 && d1>=D1 && d1<=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d1Input) <= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d1Input) >= 0) &&
                            (t1Input <= bookedRoom.getStartTime().getIdTime() &&
                                    (t2Input >= bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 6. t1<=T1 && t2>=T2 && d2>=D1 && d2<=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d2Input) <= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d2Input) >= 0) &&
                            (t1Input <= bookedRoom.getStartTime().getIdTime() &&
                                    (t2Input >= bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 7. t2>T1 && t2<=T2 && d1<=D1 && d2>=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d1Input) >= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d2Input) <= 0) &&
                            (t2Input > bookedRoom.getStartTime().getIdTime() &&
                                    (t2Input <= bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 8. t1>=T1 && t1<T2 && d1<=D1 && d2>=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d1Input) >= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d2Input) <= 0) &&
                            (t1Input >= bookedRoom.getStartTime().getIdTime() &&
                                    (t1Input < bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }

                    // case 9. t1<=T1 && t2>=T2 && d1<=D1 && d2>=D2
                    if ((TestDate.compareDates(bookedRoom.getStartDate(), d1Input) >= 0) &&
                            (TestDate.compareDates(bookedRoom.getEndDate(), d2Input) <= 0) &&
                            (t1Input <= bookedRoom.getStartTime().getIdTime() &&
                                    (t2Input >= bookedRoom.getEndTime().getIdTime()))) {
                        isAvailableRoom = false;
                        break;
                    }
                }
            } else {
                isAvailableRoom = true;
            }
            if(isAvailableRoom && meetingRoom.getRoomStatus().getIdRoomStatus() == 2){
                listDateAndTimes.add(meetingRoom);
            }
        }

        if(listDateAndTimes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<MeetingRoomSearchDTO> listSearchedResult = new ArrayList<>();
        List<AssetDetail> assetList = null;
        StringBuilder assetStringBuilder = null;
        MeetingRoomSearchDTO meetingRoomDTO = null;
        for (MeetingRoom room : listDateAndTimes){
            assetStringBuilder = new StringBuilder();
            meetingRoomDTO = new MeetingRoomSearchDTO();
            meetingRoomDTO.setIdRoom(room.getIdRoom());
            meetingRoomDTO.setRoomName(room.getRoomName());
            meetingRoomDTO.setCapacity(room.getCapacity());
            meetingRoomDTO.setZone(room.getZone());
            assetList = (List<AssetDetail>) room.getAssetDetailCollection();
            for (AssetDetail assetDetail : assetList){
                assetStringBuilder.append(assetDetail.getAsset().getAssetName());
                assetStringBuilder.append(": ");
                assetStringBuilder.append(assetDetail.getAssetQuantity());
                assetStringBuilder.append(". ");
            }
            meetingRoomDTO.setRoomAsset(String.valueOf(assetStringBuilder));
            listSearchedResult.add(meetingRoomDTO);
        }

        // filter final listSearchedResult by assets browsed
        String[] assetBrowsedList = meetingRoomSearchDTO.getRoomAsset().split("-");
        List<MeetingRoomSearchDTO> finalList = new ArrayList<>();
        if(assetBrowsedList.length == 0 || assetBrowsedList == null){
            return new ResponseEntity<>(finalList, HttpStatus.OK);
        }
        boolean assetIncluded = true;
        for(MeetingRoomSearchDTO roomSearchDTO : listSearchedResult){
            assetIncluded = true;
            for(int i = 0; i < assetBrowsedList.length; i++){
                if(!roomSearchDTO.getRoomAsset().contains(assetBrowsedList[i])){
                    assetIncluded = false;
                    break;
                }
            }

            if(assetIncluded){
                finalList.add(roomSearchDTO);
            }
        }
        if(finalList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(finalList, HttpStatus.OK);
    }

    // find timeFrame each by Id
    @GetMapping("time-frame/{id}")
    public ResponseEntity<TimeFrame> findTimeFrameById(@PathVariable long id) {
        TimeFrame timeFrame = timeFrameService.findById(id);
        if (timeFrame == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(timeFrame, HttpStatus.OK);
    }

    @GetMapping("asset-list")
    public ResponseEntity<List<AssetDTO>> getAllAssetList() {
        List<Asset> assetList = assetService.findAll();
        List<AssetDTO> assetDTOList = new ArrayList<>();
        AssetDTO assetDTO = null;
        if (assetList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Asset asset : assetList) {
            assetDTO = new AssetDTO();
            assetDTO.setAssetName(asset.getAssetName());
            assetDTOList.add(assetDTO);
        }
        return new ResponseEntity<>(assetDTOList, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<BookedRoom> deleteBookedRoom(@PathVariable Long id) {
        BookedRoom bookedRoom = bookedRoomService.findById(id);
        if (bookedRoom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookedRoomService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("findBookedRoom/{id}")
    public ResponseEntity<BookedRoomDTO> findRoomById(@PathVariable long id) {
        BookedRoom bookedRoom = bookedRoomService.findById(id);
        if (bookedRoom == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        BookedRoomDTO bookedRoomDTO = new BookedRoomDTO(
                bookedRoom.getIdBookedRoom(),
                bookedRoom.getStartDate(),
                bookedRoom.getEndDate(),
                bookedRoom.getContent(),
                bookedRoom.getBookedDate(),
                bookedRoom.getBookedStatus(),
                bookedRoom.getStartTime().getIdTime(),
                bookedRoom.getStartTime().getTimeValue(),
                bookedRoom.getEndTime().getIdTime(),
                bookedRoom.getEndTime().getTimeValue(),
                bookedRoom.getBookedUser().getIdUser(),
                bookedRoom.getMeetingRoom().getIdRoom(),
                bookedRoom.getMeetingRoom().getRoomName(),
                bookedRoom.getMeetingRoom().getRoomType().getRoomTypeName()
        );
        return new ResponseEntity<>(bookedRoomDTO, HttpStatus.OK);
    }

}
