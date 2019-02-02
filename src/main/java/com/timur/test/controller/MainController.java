package com.timur.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/hello")
@Api(value = "HelloWorld Resource", description = "shows hello world")
public class MainController {

    @ApiOperation(value = "Returns main page")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "100 is the message"),
                    @ApiResponse(code = 200, message = "Successful for main page")
            }
    )
    @GetMapping("/")
    public String greeting() {
        return "main";
    }
}

