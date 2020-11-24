package sprint_1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sprint_1.model.ErrorType;
import sprint_1.service.ErrorTypeService;

import java.util.List;

@RestController
@CrossOrigin
public class ErrorTypeController {
    @Autowired
    ErrorTypeService errorTypeService;
    @GetMapping("/errorType")
    public ResponseEntity<List<ErrorType>> getList(){
        List<ErrorType> errorTypes = errorTypeService.findAll();
        if (errorTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(errorTypes,HttpStatus.OK);
        }
    }
}
