package com.openclassrooms.services;

import com.openclassrooms.entities.AppUser;

import java.util.List;

public interface IAppUserService {

    void addUser(AppUser user);

    AppUser checkUser(String email);

    List<AppUser> getAllAppUsers();
    AppUser getAppUserById(int id);
    void deletUser(AppUser user);
    AppUser updateUser(AppUser user);

}
