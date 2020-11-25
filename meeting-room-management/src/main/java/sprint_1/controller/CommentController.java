package sprint_1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
                    commentDTO.add(new CommentDTO(commentLists.getIdComment(), commentLists.getSender().getFullName(), commentLists.getCommentTime(), "N/A",
                            commentLists.getContentComment(), "N/A", commentLists.getErrorType().getErrorTypeName(),
                            commentLists.getMeetingRoom().getRoomName(), commentLists.isStatus(),commentLists.isStatusView()));
                } else {
                    commentDTO.add(new CommentDTO(commentLists.getIdComment(), commentLists.getSender().getFullName(),
                            commentLists.getCommentTime(), commentLists.getContentReply(), commentLists.getContentComment(),
                            commentLists.getReplier().getFullName(), commentLists.getErrorType().getErrorTypeName(),
                            commentLists.getMeetingRoom().getRoomName(), commentLists.isStatus(),commentLists.isStatusView()));

                }
            }
            return new ResponseEntity<>(commentDTO, HttpStatus.OK);
        }
    }

    @GetMapping("/comment/search")
    public ResponseEntity<List<CommentDTO>> findCommentByRoomName(@RequestParam("value1") String userNameSearch, @RequestParam("value2") String roomNameSearch, @RequestParam("value3") boolean statusSearch) {
        List<Comment> listAll = commentService.findAll();
        List<Comment> commentListUserName ;
        if (listAll.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

// (1) search by userName
        if ("".equals(userNameSearch)){
            commentListUserName = listAll;
        } else {
           commentListUserName = commentService.findAllBySender(userNameSearch);
        }

// (2) search by roomName
        List<Comment> commentListRoomName = new ArrayList<>();
        if ("".equals(roomNameSearch)){
            commentListRoomName = commentListUserName;
        } else {
            for (Comment room: commentListUserName ){
                if ((room.getMeetingRoom().getRoomName().toLowerCase()).contains(roomNameSearch.toLowerCase())){
                    commentListRoomName.add(room);
                }
            }
        }
// (3) search by status
        List<Comment> commentList = new ArrayList<>();
        if (!statusSearch){
            commentList = commentListRoomName;
        } else {
            for (Comment room: commentListRoomName ){
                if (statusSearch == room.isStatus()){
                    commentList.add(room);
                }
            }
        }

// (3) convert commentDTO
        List<CommentDTO> commentListDTO = new ArrayList<>();
        for (Comment commentLists : commentList) {
                if (commentLists.getReplier() == null) {
                    commentListDTO.add(new CommentDTO(commentLists.getIdComment(), commentLists.getSender().getFullName(), commentLists.getCommentTime(), "N/A",
                            commentLists.getContentComment(), "N/A", commentLists.getErrorType().getErrorTypeName(),
                            commentLists.getMeetingRoom().getRoomName(), commentLists.isStatus(),commentLists.isStatusView()));
                } else {
                    commentListDTO.add(new CommentDTO(commentLists.getIdComment(), commentLists.getSender().getFullName(),
                            commentLists.getCommentTime(), commentLists.getContentReply(),
                            commentLists.getContentComment(), commentLists.getReplier().getFullName(), commentLists.getErrorType()
                            .getErrorTypeName(), commentLists.getMeetingRoom().getRoomName(), commentLists.isStatus(),commentLists.isStatusView()));

                }
            }
        if (commentListDTO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(commentListDTO, HttpStatus.OK);
    }

    @GetMapping("/comment/{idComment}")
    public ResponseEntity<CommentDTO> findCommentById(@PathVariable Long idComment) {

        Comment comment = commentService.findById(idComment);
        CommentDTO commentDTO;
        if (comment.getReplier() == null) {
            commentDTO = new CommentDTO(comment.getIdComment(), comment.getSender().getFullName(), comment.getCommentTime(), comment.getContentReply(),
                    comment.getContentComment(), "N/A", comment.getErrorType().getErrorTypeName(),
                    comment.getMeetingRoom().getRoomName(), comment.isStatus(),comment.isStatusView());
        } else {
            commentDTO = new CommentDTO(comment.getIdComment(), comment.getSender().getFullName(), comment.getCommentTime(), comment.getContentReply(),
                    comment.getContentComment(), comment.getReplier().getFullName(), comment.getErrorType().getErrorTypeName(),
                    comment.getMeetingRoom().getRoomName(), comment.isStatus(), comment.isStatusView());
        }
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PutMapping("/comment/{idComment}")
    public ResponseEntity<Void> addCommentHandle(@PathVariable Long idComment, @RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.findById(idComment);
        comment.setStatus(false);
        comment.setReplier(userService.findById((long) 2));
        comment.setContentReply(commentDTO.getContentReply());
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/comment/detail/{idComment}")
    public ResponseEntity<Void> detailCommentHandle(@PathVariable Long idComment, @RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.findById(idComment);
        comment.setStatusView(true);
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id){
        Comment comment= commentService.findById(id);
        commentService.remove(id);
        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @PostMapping("/comment/create")
    public ResponseEntity<Void> addComment(@Validated @RequestBody CommentDTO commentDTO , BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
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
            return new ResponseEntity<>(HttpStatus.OK);}
    }
}
