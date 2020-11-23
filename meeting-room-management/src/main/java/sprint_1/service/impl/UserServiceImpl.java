package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_1.dto.UserManagerDTO;
import sprint_1.model.User;
import sprint_1.repository.UserRepository;
import sprint_1.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserName(String username) {
        return userRepository.existsByUserName(username);
    }

    @Override
    public void changePassWord(Long id, String password) {
        User user = userRepository.findById(id).orElse(null);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public List<User> findUserByUserNameContainingAndDepartmentContaining(String userName, String department) {
        return userRepository.findUserByUserNameContainingAndDepartmentContaining(userName, department);
    }

    @Override
    public List<User> findUserByUserNameContaining(String userName) {
        return userRepository.findUserByUserNameContaining(userName);
    }

    @Override
    public List<User> findUserByDepartmentContaining(String department) {
        return userRepository.findUserByDepartmentContaining(department);
    }
}
