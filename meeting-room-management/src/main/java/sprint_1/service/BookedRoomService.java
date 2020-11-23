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

    List<BookedRoom> findAllByMeetingRoom_RoomName(String roomName);
    List<BookedRoom> findAllByMonth(String month);
    List<BookedRoom> findAllByYear(String yaer);
}
