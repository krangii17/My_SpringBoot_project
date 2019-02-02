package com.timur.test.service;

import com.timur.test.domain.Role;
import com.timur.test.domain.User;
import com.timur.test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
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

}
