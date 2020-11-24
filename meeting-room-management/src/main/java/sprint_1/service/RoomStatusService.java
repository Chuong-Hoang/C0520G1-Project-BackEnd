package sprint_1.service;

import sprint_1.model.RoomStatus;

import java.util.List;

public interface RoomStatusService {
    List<RoomStatus> findAll();

    void save(RoomStatus roomStatus);

    RoomStatus findById(Long id);

    void remove(Long id);

    RoomStatus findByName(String name);
}
