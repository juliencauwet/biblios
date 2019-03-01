package openclassrooms.services;

import openclassrooms.entities.AppUser;

import java.util.List;

public interface IAppUserService {

    void addUser(AppUser user);

    AppUser checkUser(String email);

    List<AppUser> getAllAppUsers();
    AppUser getAppUserById(int id);

}
