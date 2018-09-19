package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.*;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class AppUserAction extends ActionSupport{

    TestPortService service = new TestPortService();
    TestPort port = service.getTestPortSoap11();
    List<AppUser> appUsers = null;

    @Override
    public String execute() throws Exception {

       return SUCCESS;
    }

    public String getAllAppUsers(){
        setAppUsers(port.appUserGetAll(new AppUserGetAllRequest()).getGetAllAppUsers());
        return SUCCESS;
    }

    public List<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}
