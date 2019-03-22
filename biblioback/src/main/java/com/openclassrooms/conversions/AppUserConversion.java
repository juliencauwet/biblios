package com.openclassrooms.conversions;


import com.openclassrooms.biblioback.ws.test.AppUser;
import com.openclassrooms.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class AppUserConversion {


    @Autowired
    private AppUserService appUserService;

    public AppUser appUserEntityToAppUser(com.openclassrooms.entities.AppUser appUserEntity){
        AppUser appUser = new AppUser();
        appUser.setId(appUserEntity.getId());
        appUser.setFirstName(appUserEntity.getFirstName());
        appUser.setName(appUserEntity.getName());
        appUser.setEmail(appUserEntity.getEmail());
        appUser.setPassword(appUser.getPassword());
        appUser.setAlert(appUserEntity.getAlert());
        return appUser;
    }
//
    public com.openclassrooms.entities.AppUser appUserToAppUserEntity(AppUser appUser){
        com.openclassrooms.entities.AppUser appUserEntity = appUserService.getAppUserById(appUser.getId());
        appUserEntity.setId(appUser.getId());
        appUserEntity.setName(appUser.getName());
        appUserEntity.setFirstName(appUser.getFirstName());
        appUserEntity.setEmail(appUser.getEmail());
        appUserEntity.setPassword(appUser.getPassword());
        appUserEntity.setAlert(appUser.isAlert());
        return appUserEntity;
    }

}
