package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.RoomStatus;
import sprint_1.repository.RoomStatusRepository;
import sprint_1.service.RoomStatusService;

import java.util.List;

@Service
public class RoomStatusServiceImpl implements RoomStatusService {
    @Autowired
    private RoomStatusRepository roomStatusRepository;

    @Override
    public List<RoomStatus> findAll() {
        return roomStatusRepository.findAll();
    }

    @Override
    public void save(RoomStatus roomStatus) {
        roomStatusRepository.save(roomStatus);
    }

    @Override
    public RoomStatus findById(Long id) {
        return roomStatusRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        roomStatusRepository.deleteById(id);
    }
}
