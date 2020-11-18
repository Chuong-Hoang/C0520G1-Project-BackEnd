package sprint_1.service;

import sprint_1.model.BookedRoom;

import java.util.List;

public interface BookedRoomService {

    List<BookedRoom> findAll();

    BookedRoom findById(Long id);

    void save(BookedRoom bookedRoom);

    void deleteById(Long id);
}
