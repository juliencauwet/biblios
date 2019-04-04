package com.openclassrooms.services;

import com.openclassrooms.BibliobackApplication;
import com.openclassrooms.entities.AppUser;
import com.openclassrooms.repositories.AppUserRepository;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BibliobackApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AppUserServiceTest {

    @Autowired
    AppUserService service;

    @Autowired
    AppUserRepository appUserRepository;

    @Before
    public void init(){
        AppUser user = new AppUser();
        user.setEmail("test1@test.com");
        user.setPassword("test1");
        appUserRepository.save(user);
    }


    @Test
    public void addUser() {
    //   AppUser user = new AppUser();
    //   user.setEmail("test@test.com");
    //   user.setPassword("testtest");
    //   service.addUser(user);
    //   Assert.assertEquals("test@test.com", service.checkUser("test@test.com"));
    }

    @Test
    public void checkUser() {
        Assert.assertNotNull(service.checkUser("juliencauwet@yahoo.fr"));

    }

    @Test
    public void getAllAppUsers() {
        Assert.assertNotNull(service.getAllAppUsers());
    }

    @Test
    public void getAppUserById() {
        Assert.assertEquals("Julien", service.getAppUserById(7).getFirstName());
    }

  // @Test
  // public void deletUser() {
  //     AppUser user = appUserRepository.findByEmail("test1@test.com");
  //     service.deletUser(user);
  //     Assert.assertNull(service.checkUser("test1@test.com"));

  // }

    @Test
    public void updateUser() {
    }
}
