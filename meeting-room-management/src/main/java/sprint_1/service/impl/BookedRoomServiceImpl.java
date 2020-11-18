package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.BookedRoom;
import sprint_1.repository.BookedRoomRepository;
import sprint_1.service.BookedRoomService;

import java.util.List;

@Service
public class BookedRoomServiceImpl implements BookedRoomService {
    @Autowired
    private BookedRoomRepository bookedRoomRepository;

    @Override
    public List<BookedRoom> findAll() {
        return bookedRoomRepository.findAll();
    }

    @Override
    public BookedRoom findById(Long id) {
        return bookedRoomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(BookedRoom bookedRoom) {
        bookedRoomRepository.save(bookedRoom);
    }

    @Override
    public void deleteById(Long id) {
        bookedRoomRepository.deleteById(id);
    }
}
