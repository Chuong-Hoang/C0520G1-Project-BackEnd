package sprint_1.service;

import sprint_1.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    void save(Comment comment);

    Comment findById(Long id);

    void remove(Long id);

    List<Comment> findCommentByRoomName(String name);

    List<Comment> findAllBySender(String name);

    List<Comment> findAllByStatus(boolean status);
}
