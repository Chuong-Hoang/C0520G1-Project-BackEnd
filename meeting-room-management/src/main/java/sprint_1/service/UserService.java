package sprint_1.service;

import sprint_1.model.User;

import java.util.List;

/**
 * UserService
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
public interface UserService {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    boolean existsByUserName(String username);

    Boolean existsByPassword(String password);

    void changePassWord(Long id, String password);

    List<User> findUserByUserNameContainingAndDepartmentContaining(String userName, String department);

    List<User> findUserByUserNameContaining(String userName);

    List<User> findUserByDepartmentContaining(String department);
}
