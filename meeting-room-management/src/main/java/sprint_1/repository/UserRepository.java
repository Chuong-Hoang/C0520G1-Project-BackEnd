package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.Comment;
import sprint_1.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    Boolean existsByUserName(String username);

    Boolean existsByPassword(String password);

    User findUserByUserName(String username);

    List<User> findUserByUserNameContainingAndDepartmentContaining(String userName, String department);

    List<User> findUserByUserNameContaining(String userName);

    List<User> findUserByDepartmentContaining(String department);
}
