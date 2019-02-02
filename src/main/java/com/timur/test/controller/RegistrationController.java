package com.timur.test.controller;

import com.timur.test.domain.Role;
import com.timur.test.domain.User;
import com.timur.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/rest/registration")
@Api(value = "User Resource REST Endpoint", description = "Shows the registration info")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ApiOperation(value = "Returns User's values")
    @PostMapping("/registration")
    public User addUser(User user) {
        boolean isExist = userService.isExistUserInDB(user.getUsername());
        userService.saveUser(user);
        return new User(user.getUsername(), user.getPassword(), true, Set.of(Role.USER));
    }
}
