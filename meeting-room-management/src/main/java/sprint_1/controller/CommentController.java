package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sprint_1.model.Comment;
import sprint_1.service.CommentService;
import sprint_1.service.ErrorTypeService;

import java.util.List;

@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    ErrorTypeService errorTypeService;

    @GetMapping("/comment")
    public ResponseEntity<List<Comment>> getListBlog(){
        List<Comment> blogList = commentService.findAll();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(blogList,HttpStatus.OK);
        }
    }
}
