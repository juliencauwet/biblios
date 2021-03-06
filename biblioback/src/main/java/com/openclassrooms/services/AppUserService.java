package com.openclassrooms.services;

import com.openclassrooms.entities.AppUser;
import com.openclassrooms.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser addUser(AppUser user) {
        return appUserRepository.save(user);
    }


    @Override
    public AppUser checkUser(String email) {
        return appUserRepository.findByEmail(email);
    }

    @Override
    public List<AppUser> getAllAppUsers() {
        List<AppUser> appUsers = new ArrayList<>();
        appUserRepository.findAll().forEach(appUsers::add);
        return appUsers;
    }

    @Override
    public AppUser getAppUserById(int id) {
        return appUserRepository.findById(id);
    }

    @Override
    public void deletUser(AppUser user) {
        appUserRepository.delete(user);
    }

    @Override
    public AppUser updateUser(AppUser user) {
        return appUserRepository.save(user);
    }
}
