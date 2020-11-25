package sprint_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_1.model.User;

import java.util.List;

/**
 * UserRepository
 * <p>
 * Version 1.0
 * <p>
 * Date: 24-11-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 22-11-2020         HienTH           CRUD
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUserName(String username);

    Boolean existsByPassword(String password);

    User findUserByUserName(String username);

    List<User> findUserByUserNameContainingAndDepartmentContaining(String userName, String department);

    List<User> findUserByUserNameContaining(String userName);

    List<User> findUserByDepartmentContaining(String department);
}
