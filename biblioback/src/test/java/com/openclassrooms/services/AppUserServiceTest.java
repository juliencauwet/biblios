package com.openclassrooms.services;

import openclassrooms.BibliobackApplication;
import openclassrooms.entities.AppUser;
import openclassrooms.repositories.AppUserRepository;
import openclassrooms.services.AppUserService;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
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

    AppUser user = new AppUser(12, "test@test.com", "test1");


    @BeforeClass
    public static void init(){


    }

    @Before
    public void setUp(){
        appUserService.addUser(user);

    }




    @Test
    public void addUser() {

        appUserService.addUser(new AppUser(2, "test2@test.fr", "okokok"));
        Assert.assertEquals(2, appUserService.getAllAppUsers().size());
    }

    @Test
    public void checkUser() {
    }

    @Test
    public void getAllAppUsers() {
        for(int i = 1; i < 11 ; i++)
            appUserService.addUser(new AppUser(i,"testGetAll"+ i +"@test.com", "hello" + i));
        List<AppUser> users = appUserService.getAllAppUsers();
        Assert.assertEquals(10, users.size());
    }

 //  @Test
 //  public void getAppUserById() {
 //      AppUser user = appUserService.getAppUserById(12);
 //      Assert.assertEquals("test@test.com", user.getEmail());

 //  }

    @After
    public void close(){
        List<AppUser> users = appUserService.getAllAppUsers();
        for( AppUser user : users)
            appUserService.deletUser(user);
    }
}