package sprint_1.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

/**
 * controller StatisticRoomController
 * <p>
 * Version 1.0
 * <p>
 * Date: 24/11/2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 24/11/2020        Nguyễn Tiến Hải            statistic
 */

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


    /**
     * statistic by time
     *
     * @param startDate , endDate
     * @return list BookedRoomDTOList
     */
    @GetMapping("/searchByTime")
    public ResponseEntity<List<BookedRoomDTOList>> getListBookedRoomByTime(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
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
                for (BookedRoom bookedRoom : bookedRoomList) {
                    bookedRoomDTOLists.add(new BookedRoomDTOList(bookedRoom.getIdBookedRoom(), bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getContent(), bookedRoom.getBookedDate(), bookedRoom.getBookedStatus(), bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime()), bookedRoomService.totalUse(bookedRoom.getMeetingRoom().getRoomName()), bookedRoom.getStartTime().getTimeValue(), bookedRoom.getEndTime().getTimeValue(), bookedRoom.getBookedUser().getUserName(), bookedRoom.getMeetingRoom().getRoomName(), bookedRoom.getMeetingRoom().getRoomType().getRoomTypeName()));
                }
                return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
            }
        } else {
            return null;
        }
    }


    /**
     * statistic by room
     *
     * @param roomType , roomName , month ,year
     * @return list BookedRoomDTOList
     */
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
                for (BookedRoom bookedRoom : list) {
                    if (roomType.equals(bookedRoom.getMeetingRoom().getRoomType().getRoomTypeName())) {
                        listRoomType.add(bookedRoom);
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
                for (BookedRoom bookedRoom : listYear) {
                    bookedRoomDTOLists.add(new BookedRoomDTOList(bookedRoom.getIdBookedRoom(), bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getContent(), bookedRoom.getBookedDate(), bookedRoom.getBookedStatus(), bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime()), bookedRoomService.totalUse(bookedRoom.getMeetingRoom().getRoomName()), bookedRoom.getStartTime().getTimeValue(), bookedRoom.getEndTime().getTimeValue(), bookedRoom.getBookedUser().getUserName(), bookedRoom.getMeetingRoom().getRoomName(), bookedRoom.getMeetingRoom().getRoomType().getRoomTypeName()));
                }
                return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
            }
        }
    }


    /**
     * get all room type
     *
     * @return list RoomType
     */
    @GetMapping("/allRoomType")
    public ResponseEntity<List<RoomType>> getListRoomType() {
        List<RoomType> roomTypeList = roomTypeService.findAll();
        if (roomTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(roomTypeList, HttpStatus.OK);
        }
    }


    /**
     * get all room type
     *
     * @return list roomName
     */
    @GetMapping("/allRoomName")
    public ResponseEntity<List<String>> getListRoomName() {
        List<BookedRoom> bookedRoomList = bookedRoomService.findAll();
        List<String> roomNameDTOLists = new ArrayList<>();
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (BookedRoom bookedRoom : bookedRoomList) {
                roomNameDTOLists.add(bookedRoom.getMeetingRoom().getRoomName());
            }
            return new ResponseEntity<>(roomNameDTOLists, HttpStatus.OK);
        }
    }


    /**
     * statistic by room
     *
     * @param startYear, endYear
     * @return list BookedRoomDTOList
     */
    @GetMapping("/allDataChart")
    public ResponseEntity<List<BookedChartDTO>> getAllDataChart(@RequestParam("startYear") String startYear, @RequestParam("endYear") String endYear) {
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
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;
        int count9 = 0;
        int count10 = 0;
        int count11 = 0;
        int count12 = 0;
        if (bookedRoomList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (int year1 = Integer.parseInt(startYear); year1 <= Integer.parseInt(endYear); year1++) {
                for (BookedRoom bookedRoom : bookedRoomList) {
                    if ((bookedRoom.getStartDate()).contains((year1 + "-01"))) {
                        count1++;
                        effective1 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());
                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-02"))) {
                        count2++;
                        effective2 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-03"))) {
                        count3++;
                        effective3 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-04"))) {
                        count4++;
                        effective4 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-05"))) {
                        count5++;
                        effective5 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-06"))) {
                        count6++;
                        effective6 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-07"))) {
                        count7++;
                        effective7 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-08"))) {
                        count8++;
                        effective8 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-09"))) {
                        count9++;
                        effective9 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-10"))) {
                        count10++;
                        effective10 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-11"))) {
                        count11++;
                        effective11 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());

                    } else if ((bookedRoom.getStartDate()).contains((year1 + "-12"))) {
                        count12++;
                        effective12 += bookedRoomService.compareEffective(bookedRoom.getStartDate(), bookedRoom.getEndDate(), bookedRoom.getStartTime().getIdTime(), bookedRoom.getEndTime().getIdTime());
                    }
                }
                bookedRoomDTOMaps.put(year1, new BookedChartDTO(String.valueOf(year1), (effective1 / count1), (effective2 / count2), (effective3 / count3), (effective4 / count4), (effective5 / count5), (effective6 / count6), (effective7 / count7), (effective8 / count8), (effective9 / count9), (effective10 / count10), (effective11 / count11), (effective12 / count12)));
            }

            for (Integer key : bookedRoomDTOMaps.keySet()) {
                bookedRoomDTOLists.add(bookedRoomDTOMaps.get(key));
            }
        }
        return new ResponseEntity<>(bookedRoomDTOLists, HttpStatus.OK);
    }
}
