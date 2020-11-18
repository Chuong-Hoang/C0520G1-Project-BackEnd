package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.TimeFrame;
import sprint_1.repository.TimeFrameRepository;
import sprint_1.service.TimeFrameService;

import java.util.List;

@Service
public class TimeFrameServiceImpl implements TimeFrameService {
    @Autowired
    TimeFrameRepository timeFrameRepository;

    @Override
    public List<TimeFrame> findAll() {
        return timeFrameRepository.findAll();
    }

    @Override
    public void save(TimeFrame timeFrame) {
        timeFrameRepository.save(timeFrame);
    }

    @Override
    public TimeFrame findById(Long id) {
        return timeFrameRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        timeFrameRepository.deleteById(id);
    }
}
