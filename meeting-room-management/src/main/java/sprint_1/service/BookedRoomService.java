package sprint_1.service;

import sprint_1.model.BookedRoom;

import java.text.ParseException;
import java.util.List;

public interface BookedRoomService {

    List<BookedRoom> findAll();

    BookedRoom findById(Long id);

    void save(BookedRoom bookedRoom);

    void deleteById(Long id);

    List<BookedRoom> searchTime( String startDate, String endDate) throws ParseException;

    /**
     * total use room
     * create Nguyen Tien Hai
     *
     * @return  int
     */
    int totalUse(String a);

    /**
     * validate Date
     * create Nguyen Tien Hai
     *
     * @return  boolean
     */
    boolean validateDate(String a , String b);

    /**
     * find BookedRoom by room name
     * create Nguyen Tien Hai
     *
     * @return list BookedRoom
     */
    List<BookedRoom> findAllByMeetingRoom_RoomName(String roomName);

    /**
     * find BookedRoom by month
     * create Nguyen Tien Hai
     *
     * @return list BookedRoom
     */
    List<BookedRoom> findAllByMonth(String month);

    /**
     * find BookedRoom by year
     * create Nguyen Tien Hai
     *
     * @return list BookedRoom
     */
    List<BookedRoom> findAllByYear(String yaer);

    /**
     * compare Effective by room
     * create Nguyen Tien Hai
     *
     * @return double
     */
    double compareEffective(String startDate, String endDate, Long startTime, Long endTime);
}
