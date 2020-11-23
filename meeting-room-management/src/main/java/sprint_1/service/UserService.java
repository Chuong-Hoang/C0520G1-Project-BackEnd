package sprint_1.service;

import sprint_1.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);

    boolean existsByUserName(String username);

    void changePassWord(Long id, String password);

}
