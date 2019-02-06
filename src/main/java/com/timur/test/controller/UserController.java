package com.timur.test.controller;

import com.timur.test.domain.User;
import com.timur.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(value = "User Controller", description = "Controller for user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Returns User's values")
    @PostMapping("/registration")
    public HttpEntity<?> addUser(User user) {
        boolean isExist = userService.isExistUserInDB(user.getUsername());
        if (!isExist) {
            return ResponseEntity.EMPTY;
        }
        userService.saveUser(user);
        return ResponseEntity.ok("User has been registered correctly");
    }

    @ApiOperation(value = "Change User's password")
    @PutMapping("/changePassword")
    public HttpEntity<?> changePassword(@RequestParam(required = true) String userName,
                                        @RequestParam(required = true) String password) {
        boolean isExist = userService.isExistUserInDB(userName);
        if (isExist) {
            return ResponseEntity.EMPTY;
        }
        User user = userService.getUserByUserName(userName);
        userService.changePassword(user, password);
        return ResponseEntity.ok("Password has been changed successfully");
    }

    @ApiOperation(value = "Get User's data by id")
    @GetMapping("/userAccountInfo")
    public String getUserAccount(@RequestParam(required = true) Long id) {
        Optional<User> user = userService.getUser(id);
        return user.map(Object::toString).orElse("{}");
    }

    @ApiOperation(value = "Delete User's data by id")
    @DeleteMapping("/delete")
    public HttpEntity<?> deleteAccount(@RequestParam(required = true) Long id, String userName) {
        boolean isExist = userService.isExistUserInDB(userName);
        if (isExist) {
            userService.deleteAccount(id);
            return ResponseEntity.ok("Account has been deleted successfully");
        }
        return ResponseEntity.EMPTY;
    }
}
