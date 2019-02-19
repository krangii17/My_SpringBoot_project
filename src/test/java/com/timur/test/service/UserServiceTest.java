package com.timur.test.service;

import com.timur.test.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application.properties")
@Sql(value = {"/create-contact-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-contact-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserService service;

    @Test
    public void isExistUserInDBByUserName() {
        //GIVEN
        boolean expected = false;
        //WHEN
        boolean actual  = service.isExistUserInDBByUserName("Boris");
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void UserIsNotExistInDBByUserName() {
        //GIVEN
        boolean expected = true;
        //WHEN
        boolean actual  = service.isExistUserInDBByUserName("Mike");
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void isExistUserInDBByID() {
        //GIVEN
        boolean expected = false;
        //WHEN
        boolean actual  = service.isExistUserInDBByID(1L);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void UserIsNotExistInDBByID() {
        //GIVEN
        boolean expected = true;
        //WHEN
        boolean actual  = service.isExistUserInDBByID(11L);
        //THEN
        assertEquals(expected, actual);
    }


    @Test
    public void getUserByUserName() {
        //GIVEN
        User expectedUser = new User( "Boris","1999",true, Set.of());
        expectedUser.setId(1L);
        expectedUser.setMessages(Set.of());
        //WHEN
        User actualUser = service.getUserByUserName("Boris");
        //THEN
        assertEquals(expectedUser,actualUser);
    }

    @Test
    public void getUser() {
        //GIVEN
        User expectedUser = new User( "Boris","1999",true, Set.of());
        expectedUser.setId(1L);
        expectedUser.setMessages(Set.of());
        //WHEN
        User actualUser = service.getUser(1L);
        //THEN
        assertEquals(expectedUser,actualUser);
    }

    @Test
    public void getUsers() {
        //WHEN
        List<User> getUsers = service.getUsers();
        //THEN
        assertNotNull(getUsers);
    }
}