package com.timur.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@RestController
@RequestMapping("/rest/login")
@Api(value = "User login Endpoint", description = "Login controller")
public class LoginController {

    @ApiOperation(value = "Login user")
    @PostMapping("/login")
    public String addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        return "Good";
    }
}
