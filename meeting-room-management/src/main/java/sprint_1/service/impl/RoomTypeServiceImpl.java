package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.RoomType;
import sprint_1.repository.RoomTypeRepository;
import sprint_1.service.RoomTypeService;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }
}
