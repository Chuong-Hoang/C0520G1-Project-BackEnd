package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.CommentDTO;
import sprint_1.model.Comment;
import sprint_1.service.CommentService;
import sprint_1.service.ErrorTypeService;
import sprint_1.service.MeetingRoomService;
import sprint_1.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
    @Autowired
    MeetingRoomService meetingRoomService;

    @Autowired
    ErrorTypeService errorTypeService;

    @GetMapping("/comment")
    public ResponseEntity<List<CommentDTO>> getListComment() {
        List<CommentDTO> commentDTO = new ArrayList<>();
        List<Comment> commentList = commentService.findAll();
        if (commentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (Comment commentLists : commentList) {
                if (commentLists.getReplier() == null) {
                    commentDTO.add(new CommentDTO(commentLists.getIdComment(), commentLists.getSender().getFullName(),commentLists.getCommentTime(),null, "N/A",
                            commentLists.getContentComment(), "N/A" , commentLists.getErrorType().getErrorTypeName(),
                            commentLists.getMeetingRoom().getRoomName(),commentLists.isStatus()));
                } else {
                    commentDTO.add(new CommentDTO(commentLists.getIdComment(), commentLists.getSender().getFullName(),commentLists.getCommentTime(), commentLists.getReplier().getIdUser(),commentLists.getContentReply(),
                            commentLists.getContentComment(), commentLists.getReplier().getFullName(), commentLists.getErrorType().getErrorTypeName(),
                            commentLists.getMeetingRoom().getRoomName(),commentLists.isStatus()));

                }
            }
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        }
    }
    @GetMapping("/comment/{idComment}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable Long idComment){
        CommentDTO commentDTO = new CommentDTO();
        Comment comment = commentService.findById(idComment);
            commentDTO = new CommentDTO(comment.getIdComment(), comment.getSender().getFullName(),comment.getCommentTime(),null, "N/A",
                    comment.getContentComment(), "N/A" , comment.getErrorType().getErrorTypeName(),
                    comment.getMeetingRoom().getRoomName(),comment.isStatus());

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

   
    @PostMapping("/comment")
    public ResponseEntity<Void> addComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCommentTime(String.valueOf(LocalDate.now()));
        comment.setContentComment(commentDTO.getContentComment());
        comment.setStatus(true);
        comment.setSender(userService.findById((long) 1));
        comment.setMeetingRoom(meetingRoomService.findByRoomName(commentDTO.getRoomName()));
        comment.setErrorType(errorTypeService.findByErrorTypeName(commentDTO.getErrorTypeName()));
        comment.setReplier(null);
        comment.setContentReply(null);
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/comment/{idComment}")
    public ResponseEntity<Void> adCommentHandle(@PathVariable Long idComment ,@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.findById(idComment);
        comment.setStatus(false);
        comment.setReplier(userService.findById((long) 2));
        comment.setContentReply(commentDTO.getContentReply());
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
