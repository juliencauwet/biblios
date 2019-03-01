package com.openclassrooms.services;

import openclassrooms.BibliobackApplication;
import openclassrooms.entities.AppUser;
import openclassrooms.services.AppUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BibliobackApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AppUserServiceTest {

    @Autowired
    AppUserService appUserService;

    @Test
    public void addUser() {
        appUserService.addUser(new AppUser("test@test.com", "test1"));
        Assert.assertEquals(1, appUserService.getAllAppUsers().size());
    }

    @Test
    public void checkUser() {
    }

    @Test
    public void getAllAppUsers() {
        List<AppUser> users = appUserService.getAllAppUsers();
        Assert.assertEquals(100, users.size());
    }

    @Test
    public void getAppUserById() {
    }
}