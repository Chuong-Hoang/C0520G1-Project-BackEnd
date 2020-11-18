package sprint_1.service;

import sprint_1.model.TimeFrame;

import java.util.List;

public interface TimeFrameService {

    List<TimeFrame> findAll();

    void save(TimeFrame timeFrame);

    TimeFrame findById(Long id);

    void remove(Long id);
}
