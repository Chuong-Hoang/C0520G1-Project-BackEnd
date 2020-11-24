package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.BookedChartDTO;
import sprint_1.dto.BookedRoomDTOList;
import sprint_1.model.BookedRoom;
import sprint_1.model.RoomType;
import sprint_1.service.BookedRoomService;
import sprint_1.service.MeetingRoomService;
import sprint_1.service.RoomTypeService;

import javax.naming.AuthenticationException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@CrossOrigin
@RequestMapping("/statistic")
public class StatisticRoomController {
    @Autowired
    BookedRoomService bookedRoomService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    MeetingRoomService meetingRoomService;

    @GetMapping("/all")
    public ResponseEntity<List<BookedRoomDTOList>> getListBookedRoom() {
        List<BookedRoom> bookedRoomList = bookedRoomService.findAll();
        List<BookedRoomDTOList> bookedRoomDTOLists = new ArrayList<>();
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (BookedRoom a : bookedRoomList) {
                bookedRoomDTOLists.add(new BookedRoomDTOList(a.getIdBookedRoom(), a.getStartDate(), a.getEndDate(), a.getContent(), a.getBookedDate(), a.getBookedStatus(), bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime()), bookedRoomService.totalUse(a.getMeetingRoom().getRoomName()), a.getStartTime().getTimeValue(), a.getEndTime().getTimeValue(), a.getBookedUser().getUserName(), a.getMeetingRoom().getRoomName(), a.getMeetingRoom().getRoomType().getRoomTypeName()));
            }
        }
        return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
    }

    @GetMapping("/searchByTime")
    public ResponseEntity<List<BookedRoomDTOList>> getListBookedRoomByTime(@RequestParam("param1") String startDate, @RequestParam("param2") String endDate) {
        List<BookedRoom> bookedRoomList = null;
        List<BookedRoomDTOList> bookedRoomDTOLists = new ArrayList<>();
        if (bookedRoomService.validateDate(startDate, endDate)) {
            try {
                bookedRoomList = bookedRoomService.searchTime(startDate, endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert bookedRoomList != null;
            if (bookedRoomList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                for (BookedRoom a : bookedRoomList) {
                    bookedRoomDTOLists.add(new BookedRoomDTOList(a.getIdBookedRoom(), a.getStartDate(), a.getEndDate(), a.getContent(), a.getBookedDate(), a.getBookedStatus(), bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime()), bookedRoomService.totalUse(a.getMeetingRoom().getRoomName()), a.getStartTime().getTimeValue(), a.getEndTime().getTimeValue(), a.getBookedUser().getUserName(), a.getMeetingRoom().getRoomName(), a.getMeetingRoom().getRoomType().getRoomTypeName()));
                }
                return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
            }
        }else {
            return null;
        }
    }

    @GetMapping("/searchByRoom")
    public ResponseEntity<List<BookedRoomDTOList>> getListBookedRoomByRoom(@RequestParam("roomType") String roomType, @RequestParam("roomName") String roomName, @RequestParam("month") String month, @RequestParam("year") String year) {
        // (*) find all
        List<BookedRoom> list = bookedRoomService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<BookedRoomDTOList> bookedRoomDTOLists = new ArrayList<>();
            // (1) search by roomType
            List<BookedRoom> listRoomType = new ArrayList<>();
            // a. roomType == '';
            if ("".equals(roomType)) {
                listRoomType = list;
            } else {
                // b. filter by 'roomType'
                for (BookedRoom room : list) {
                    if (roomType.equals(room.getMeetingRoom().getRoomType().getRoomTypeName())) {
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
            if (listYear.isEmpty() || ("".equals(roomType) && "".equals(roomName) && "".equals(month) && "".equals(year))) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                for (BookedRoom b : listYear) {
                    bookedRoomDTOLists.add(new BookedRoomDTOList(b.getIdBookedRoom(), b.getStartDate(), b.getEndDate(), b.getContent(), b.getBookedDate(), b.getBookedStatus(), bookedRoomService.compareEffective(b.getStartDate(), b.getEndDate(), b.getStartTime().getIdTime(), b.getEndTime().getIdTime()), bookedRoomService.totalUse(b.getMeetingRoom().getRoomName()), b.getStartTime().getTimeValue(), b.getEndTime().getTimeValue(), b.getBookedUser().getUserName(), b.getMeetingRoom().getRoomName(), b.getMeetingRoom().getRoomType().getRoomTypeName()));
                }
                return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
            }
        }
    }

    @GetMapping("/allRoomType")
    public ResponseEntity<List<RoomType>> getListRoomType() {
        List<RoomType> roomTypeList = roomTypeService.findAll();
        if (roomTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(roomTypeList, HttpStatus.OK);
        }
    }

    @GetMapping("/allRoomName")
    public ResponseEntity<List<String>> getListRoomName() {
        List<BookedRoom> bookedRoomList = bookedRoomService.findAll();
        List<String> roomNameDTOLists = new ArrayList<>();
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (BookedRoom a : bookedRoomList) {
                roomNameDTOLists.add(a.getMeetingRoom().getRoomName());
            }
            return new ResponseEntity<>(roomNameDTOLists, HttpStatus.OK);
        }
    }

    @GetMapping("/allDataChart")
    public ResponseEntity<List<BookedChartDTO>> getAllDataChart(@RequestParam("start") String start, @RequestParam("end") String end) {
        List<BookedRoom> bookedRoomList = bookedRoomService.findAll();
        Map<Integer, BookedChartDTO> bookedRoomDTOMaps = new TreeMap<>();
        List<BookedChartDTO> bookedRoomDTOLists = new ArrayList<>();
        double effective1 = 0;
        double effective2 = 0;
        double effective3 = 0;
        double effective4 = 0;
        double effective5 = 0;
        double effective6 = 0;
        double effective7 = 0;
        double effective8 = 0;
        double effective9 = 0;
        double effective10 = 0;
        double effective11 = 0;
        double effective12 = 0;
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (int year1 = Integer.parseInt(start); year1 <= Integer.parseInt(end); year1++) {
                for (BookedRoom a : bookedRoomList) {
                    if ((a.getStartDate()).contains((year1 + "-01"))) {
                        effective1 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());
                    } else if ((a.getStartDate()).contains((year1 + "-02"))) {
                        effective2 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-03"))) {
                        effective3 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-04"))) {
                        effective4 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-05"))) {
                        effective5 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-06"))) {
                        effective6 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-07"))) {
                        effective7 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-08"))) {
                        effective8 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-09"))) {
                        effective9 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-10"))) {
                        effective10 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-11"))) {
                        effective11 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());

                    } else if ((a.getStartDate()).contains((year1 + "-12"))) {
                        effective12 += bookedRoomService.compareEffective(a.getStartDate(), a.getEndDate(), a.getStartTime().getIdTime(), a.getEndTime().getIdTime());
                    }
                }
                bookedRoomDTOMaps.put(year1, new BookedChartDTO(String.valueOf(year1), effective1, effective2, effective3, effective4, effective5, effective6, effective7, effective8, effective9, effective10, effective11, effective12));
            }

            for (Integer key : bookedRoomDTOMaps.keySet()) {
                bookedRoomDTOLists.add(bookedRoomDTOMaps.get(key));
            }
        }
        return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
    }
}
