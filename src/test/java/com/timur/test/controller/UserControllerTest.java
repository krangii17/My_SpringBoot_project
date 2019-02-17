package com.timur.test.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application.properties")
@Sql(value = {"/create-contact-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-contact-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
        mvc = null;
    }

    @Test
    public void getNotExistUserAccount() throws Exception {
        //GIVEN
        String expectedResponse = "";
        int expectedStatus = 404;
        String uri = "/user/userAccountInfo?id=9";
        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String actualResponse = mvcResult.getResponse().getContentAsString();
        //THEN
        assertEquals(actualResponse, expectedResponse);
        assertEquals(expectedStatus, status);
        assertNotNull(result);
    }

    @Test
    public void getExistUserAccount() throws Exception {
        //GIVEN
        String expectedResponse = "{\"id\":1,\"username\":\"Boris\"," +
                "\"password\":\"1999\",\"active\":true,\"roles\":[],\"messages\":[]}";
        String uri = "/user/userAccountInfo?id=1";
        int expectedStatus = 200;
        //WHEN
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        String result = mvcResult.getResponse().getContentAsString();
        String actualResponse = mvcResult.getResponse().getContentAsString();
        //THEN
        assertEquals(actualResponse, expectedResponse);
        assertEquals(expectedStatus, status);
        assertNotNull(result);
    }

    @Test
    public void getAllUsers() {
    }
}