package openclassrooms.services;

import openclassrooms.entities.AppUser;
import openclassrooms.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements IAppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void addUser(AppUser user) {
        appUserRepository.save(user);
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
}
