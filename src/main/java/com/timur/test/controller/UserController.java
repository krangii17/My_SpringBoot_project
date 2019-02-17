package com.timur.test.controller;

import com.timur.test.domain.User;
import com.timur.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(value = "User Controller", description = "Controller for user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Returns User's values")
    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        boolean isExist = userService.isExistUserInDBByUserName(user.getUsername());
        if (!isExist) {
            return ResponseEntity.notFound().build();
        }
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @ApiOperation(value = "Change User's password")
    @PutMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> changePassword(@RequestBody String userName,
                                               @RequestBody String password) {
        boolean isExist = userService.isExistUserInDBByUserName(userName);
        if (isExist) {
            return ResponseEntity.notFound().build();
        }
        User user = userService.getUserByUserName(userName);
        return ResponseEntity.ok(userService.changePassword(user, password));
    }

    @ApiOperation(value = "Get User's data by id")
    @GetMapping(value = "/userAccountInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserAccount(Long id) {
        return Optional
                .ofNullable(userService.getUser(id))
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get all users")
    @GetMapping(value = "/usersAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        return Optional
                .ofNullable(userService.getUsers())
                .map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Delete User's data by id")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestBody Long id) {
        boolean isExist = userService.isExistUserInDBByID(id);
        if (isExist) {
            userService.deleteAccount(id);
            return ResponseEntity.ok("Account has been deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
