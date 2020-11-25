package sprint_1.service;

import sprint_1.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUserName(String name);

    void save(User user);

    void deleteById(Long id);

    boolean existsByUserName(String username);

    Boolean existsByPassword(String password);

    void changePassWord(Long id, String password);

    List<User> findUserByUserNameContainingAndDepartmentContaining(String userName, String department);

    List<User> findUserByUserNameContaining(String userName);

    List<User> findUserByDepartmentContaining(String department);
}
