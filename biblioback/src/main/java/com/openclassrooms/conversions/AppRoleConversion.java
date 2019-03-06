package com.openclassrooms.conversions;

import com.openclassrooms.entities.AppRole;
import com.openclassrooms.services.AppRoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class AppRoleConversion {

    @Autowired
    private AppRoleService appRoleService;

    public AppRole appRoleEntityToAppRole(AppRole appRoleEntity){
        AppRole appRole= new AppRole();
        appRole.setId(appRoleEntity.getId());
        appRole.setName(appRoleEntity.getName());
        appRole.setUsers(appRoleEntity.getUsers());
        return appRole;
    }
    //
    public com.openclassrooms.entities.AppRole appRoleToAppRoleEntity(AppRole appRole){
        com.openclassrooms.entities.AppRole appRoleEntity = appRoleService.getAppRoleById(appRole.getId());
        appRoleEntity.setId(appRole.getId());
        appRoleEntity.setName(appRole.getName());
        appRoleEntity.setUsers(appRole.getUsers());
        return appRoleEntity;
    }


}
