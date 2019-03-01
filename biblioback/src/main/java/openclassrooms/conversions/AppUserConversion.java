package openclassrooms.conversions;


import openclassrooms.biblioback.ws.test.AppUser;
import openclassrooms.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class AppUserConversion {


    @Autowired
    private AppUserService appUserService;

    public AppUser appUserEntityToAppUser(openclassrooms.entities.AppUser appUserEntity){
        AppUser appUser = new AppUser();
        appUser.setId(appUserEntity.getId());
        appUser.setFirstName(appUserEntity.getFirstName());
        appUser.setName(appUserEntity.getName());
        appUser.setEmail(appUserEntity.getEmail());
        appUser.setPassword(appUser.getPassword());
        return appUser;
    }
//
    public openclassrooms.entities.AppUser appUserToAppUserEntity(AppUser appUser){
        openclassrooms.entities.AppUser appUserEntity = appUserService.getAppUserById(appUser.getId());
        appUserEntity.setId(appUser.getId());
        appUserEntity.setName(appUser.getName());
        appUserEntity.setFirstName(appUser.getFirstName());
        appUserEntity.setEmail(appUser.getEmail());
        appUserEntity.setPassword(appUser.getPassword());
        return appUserEntity;
    }

}
