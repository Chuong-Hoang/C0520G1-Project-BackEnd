package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserName(String username);
}
