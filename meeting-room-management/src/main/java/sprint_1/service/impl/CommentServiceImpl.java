package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.model.Comment;
import sprint_1.repository.CommentRepository;
import sprint_1.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findCommentByRoomName(String name) {
        return commentRepository.findAllByMeetingRoom_RoomNameContains(name);
    }

    @Override
    public List<Comment> findAllBySender(String name) {
        return commentRepository.findAllBySender_FullNameContains(name);
    }

    @Override
    public List<Comment> findAllByStatus(boolean status) {
        return commentRepository.findAllByStatus(status);
    }

    @Override
    public List<Comment> findAllBySender_UserName(String name) {
        return commentRepository.findAllBySender_UserNameOrderByCommentTimeDesc(name);
    }
}
