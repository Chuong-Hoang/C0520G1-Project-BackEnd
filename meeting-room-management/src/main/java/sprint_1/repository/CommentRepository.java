package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByMeetingRoom_RoomNameContains(String name);
    List<Comment> findAllBySender_FullNameContains(String name);
    List<Comment> findAllByStatus(boolean status);
}
