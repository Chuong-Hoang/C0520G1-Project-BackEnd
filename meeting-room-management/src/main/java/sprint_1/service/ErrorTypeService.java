package sprint_1.service;

import sprint_1.model.ErrorType;

import java.util.List;

public interface ErrorTypeService {
    ErrorType findByErrorTypeName (String name);

    List<ErrorType> findAll();

    void save(ErrorType errorType);

    ErrorType findById(Long id);

    void remove(Long id);
}
