package com.timur.test.service;

import com.timur.test.domain.Role;
import com.timur.test.domain.User;
import com.timur.test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public boolean isExistUserInDBByUserName(String userName) {
        User userFromDb = userRepo.findByUsername(userName);
        if (userFromDb != null) {
            return false;
        }
        return true;
    }

    public boolean isExistUserInDBByID(Long id) {
        Optional<User> userFromDb = userRepo.findById(id);
        return !userFromDb.isPresent();
    }

    public User getUserByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }

    public User saveUser(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return user;
    }

    public User getUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(null);
    }

    public User changePassword(User user, String password) {
        user.setPassword(password);
        return userRepo.save(user);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void deleteAccount(Long id) {
        userRepo.deleteById(id);
    }
}
