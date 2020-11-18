package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.ErrorType;
import sprint_1.repository.ErrorTypeRepository;
import sprint_1.service.ErrorTypeService;

import java.util.List;

@Service
public class ErrorTypeServiceImpl implements ErrorTypeService {
    @Autowired
    ErrorTypeRepository errorTypeRepository;

    @Override
    public List<ErrorType> findAll() {
        return errorTypeRepository.findAll();
    }
}
