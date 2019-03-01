package openclassrooms.services;

import openclassrooms.entities.AppRole;
import openclassrooms.repositories.AppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppRoleService {

    @Autowired
    AppRoleRepository appRoleRepository;

    public AppRole getAppRoleById(int id){
        return appRoleRepository.getById(id);
    }
}
