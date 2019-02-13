package com.timur.test.controller;

import com.timur.test.domain.User;
import com.timur.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/registration",produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> addUser(@RequestBody User user) {
        boolean isExist = userService.isExistUserInDB(user.getUsername());
        if (!isExist) {
            return ResponseEntity.EMPTY;
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @ApiOperation(value = "Change User's password")
    @PutMapping(value = "/changePassword",produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> changePassword(@RequestBody String userName,
                                        @RequestBody String password) {
        boolean isExist = userService.isExistUserInDB(userName);
        if (isExist) {
            return ResponseEntity.EMPTY;
        }
        User user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(userService.changePassword(user, password));
    }

    @ApiOperation(value = "Get User's data by id")
    @GetMapping(value = "/userAccountInfo" , produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserAccount(@RequestBody Long id) {
        Optional<User> user = userService.getUser(id);
        return user.orElse(null);
    }

    @ApiOperation(value = "Delete User's data by id")
    @DeleteMapping("/delete")
    public HttpEntity<?> deleteAccount(@RequestBody Long id,
                                       @RequestBody String userName) {
        boolean isExist = userService.isExistUserInDB(userName);
        if (isExist) {
            userService.deleteAccount(id);
            return ResponseEntity.ok("Account has been deleted successfully");
        }
        return ResponseEntity.EMPTY;
    }
}
