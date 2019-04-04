package com.openclassrooms.endpoints;

import com.openclassrooms.biblioback.ws.test.AppUserValidityCheckRequest;
import com.openclassrooms.endpoints.AppUserEndPoint;
import com.openclassrooms.entities.AppUser;
import com.openclassrooms.services.AppUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppUserEndPointTest {

    @Mock
    AppUserService appUserService;

    @InjectMocks
    AppUserEndPoint endPoint;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAppUser() {
    }
/*
    @Test
    public void checkUser() {

        AppUserValidityCheckRequest request = new AppUserValidityCheckRequest();
        AppUser appUser = new AppUser();
        appUser.setEmail("test@test.com");
        appUser.setPassword("1234");
        request.setEmail("test@test.com");
        request.setPassword("1234");
        when(appUserService.checkUser(request.getEmail())).thenReturn(appUser);
        Assert.assertEquals("test@test.com", endPoint.checkUser(request).getUser().getEmail());

    }
*/
    @Test
    public void getAllAppUsers() {
    }

    @Test
    public void updateAppUser() {
    }
}
