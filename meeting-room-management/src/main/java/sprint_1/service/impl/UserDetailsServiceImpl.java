package sprint_1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sprint_1.model.User;
import sprint_1.model.UserDetailsImpl;
import sprint_1.repository.UserRepository;

/**
 * UserDetailsServiceImpl
 *
 * Version 1.0
 *
 * Date: 24/11/2020
 *
 * Copyright
 *
 * Author: Le Toan
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Not Found!!!");
        }
        return UserDetailsImpl.build(user);
    }
}
