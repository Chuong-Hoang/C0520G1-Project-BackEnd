package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sprint_1.dto.BookedRoomDTOList;
import sprint_1.model.BookedRoom;
import sprint_1.model.RoomType;
import sprint_1.service.BookedRoomService;
import sprint_1.service.RoomTypeService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/statistic")
public class StatisticRoomController {
    @Autowired
    BookedRoomService bookedRoomService;

    @Autowired
    RoomTypeService roomTypeService;

    @GetMapping("/all")
    public ResponseEntity<List<BookedRoomDTOList>> getListBookedRoom(){
        List<BookedRoom> bookedRoomList = bookedRoomService.findAll();
        List<BookedRoomDTOList> bookedRoomDTOLists = new ArrayList<>();
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            for (BookedRoom a : bookedRoomList) {
                bookedRoomDTOLists.add(new BookedRoomDTOList(a.getIdBookedRoom(),a.getStartDate(),a.getEndDate(),a.getContent(),a.getBookedDate(),a.getBookedStatus(),a.getStartTime().getTimeValue(),a.getEndTime().getTimeValue(),a.getBookedUser().getUserName(),a.getMeetingRoom().getRoomName(),a.getMeetingRoom().getRoomType().getRoomTypeName()));
            }
        }
        return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
    }

    @GetMapping("/searchByTime")
    public ResponseEntity<List<BookedRoomDTOList>> getListBookedRoomByTime(@RequestParam("param1") String startDate, @RequestParam("param2") String endDate){
        List<BookedRoom> bookedRoomList = null;
        List<BookedRoomDTOList> bookedRoomDTOLists = new ArrayList<>();
        try {
            bookedRoomList = bookedRoomService.searchTime(startDate,endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert bookedRoomList != null;
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            for (BookedRoom a : bookedRoomList) {
                bookedRoomDTOLists.add(new BookedRoomDTOList(a.getIdBookedRoom(),a.getStartDate(),a.getEndDate(),a.getContent(),a.getBookedDate(),a.getBookedStatus(),a.getStartTime().getTimeValue(),a.getEndTime().getTimeValue(),a.getBookedUser().getUserName(),a.getMeetingRoom().getRoomName(),a.getMeetingRoom().getRoomType().getRoomTypeName()));
            }
            return new ResponseEntity<>(bookedRoomDTOLists,HttpStatus.OK);
        }
    }

    @GetMapping("/searchByRoom")
    public ResponseEntity<List<BookedRoomDTOList>> getListBookedRoomByRoom(@RequestParam("roomType") String roomType, @RequestParam("roomName") String roomName,@RequestParam("month") String month, @RequestParam("year") String year){
        // (*) find all
        List<BookedRoom> list = bookedRoomService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            List<BookedRoomDTOList> bookedRoomDTOLists = new ArrayList<>();
            // (1) search by roomType
            List<BookedRoom> listRoomType = new ArrayList<>();
            // a. roomType == '';
            if ("".equals(roomType)) {
                listRoomType = list;
            } else {
                // b. filter by 'roomType'
                for(BookedRoom room : list){
                    if(roomType.equals(room.getMeetingRoom().getRoomType().getRoomTypeName())){
                        listRoomType.add(room);
                    }
                }
            }

            // (2) search by roomName
            List<BookedRoom> listRoomName = new ArrayList<>();
            // a. roomName == '';
            if ("".equals(roomName)) {
                listRoomName = listRoomType;
            } else {
                // b. filter by 'roomName'
                listRoomName = bookedRoomService.findAllByMeetingRoom_RoomName(roomName);
            }

            // (3) search by month
            List<BookedRoom> listMonth = new ArrayList<>();
            // a. month == '';
            if ("".equals(month)) {
                listMonth = listRoomName;
            } else {
                // b. filter by 'month'
                listMonth = bookedRoomService.findAllByMonth(month);
            }

            // (3) search by year
            List<BookedRoom> listYear = new ArrayList<>();
            // a. year == '';
            if ("".equals(year)) {
                listYear = listMonth;
            } else {
                // b. filter by 'year'
                listYear = bookedRoomService.findAllByYear(year);
            }
            if(listYear.isEmpty() ||  ("".equals(roomType) && "".equals(roomName) && "".equals(month) && "".equals(year))){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                for (BookedRoom b : listYear) {
                    bookedRoomDTOLists.add(new BookedRoomDTOList(b.getIdBookedRoom(),b.getStartDate(),b.getEndDate(),b.getContent(),b.getBookedDate(),b.getBookedStatus(),b.getStartTime().getTimeValue(),b.getEndTime().getTimeValue(),b.getBookedUser().getUserName(),b.getMeetingRoom().getRoomName(),b.getMeetingRoom().getRoomType().getRoomTypeName()));
                }
                return new ResponseEntity<>(bookedRoomDTOLists,HttpStatus.OK);
            }
        }
    }

    @GetMapping("/allRoomType")
    public ResponseEntity<List<RoomType>> getListRoomType(){
        List<RoomType> roomTypeList = roomTypeService.findAll();
        if (roomTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(roomTypeList,HttpStatus.OK);
        }
    }

    @GetMapping("/allRoomZone")
    public ResponseEntity<List<String>> getListZone(){
        List<BookedRoom> bookedRoomList = bookedRoomService.findAll();
        List<String> zoneDTOLists = new ArrayList<>();
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            for (BookedRoom a : bookedRoomList) {
                zoneDTOLists.add(a.getMeetingRoom().getZone());
            }
            return new ResponseEntity<>(zoneDTOLists,HttpStatus.OK);
        }
    }
    
    @GetMapping("/allRoomName")
    public ResponseEntity<List<String>> getListRoomName(){
        List<BookedRoom> bookedRoomList = bookedRoomService.findAll();
        List<String> roomNameDTOLists = new ArrayList<>();
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            for (BookedRoom a : bookedRoomList) {
                roomNameDTOLists.add(a.getMeetingRoom().getRoomName());
            }
            return new ResponseEntity<>(roomNameDTOLists,HttpStatus.OK);
        }
    }
}
