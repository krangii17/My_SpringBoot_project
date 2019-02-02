package com.timur.test.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/userInfo")
@Api(value = "User info Endpoint", description = "Shows the user info")
public class UserInfoController {

    @GetMapping("/userAccountInfo")
    public String getUserAccount() {
        return "userAccountInfo";
    }

}
