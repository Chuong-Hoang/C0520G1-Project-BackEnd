package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
