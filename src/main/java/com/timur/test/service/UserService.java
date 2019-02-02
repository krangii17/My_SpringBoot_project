package com.timur.test.service;

import com.timur.test.domain.Role;
import com.timur.test.domain.User;
import com.timur.test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public boolean isExistUserInDB(String userName) {
        User userFromDb = userRepo.findByUsername(userName);
        if (userFromDb != null) {
            return false;
        }
        return true;
    }

    public void saveUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }
}
