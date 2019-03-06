package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.AppUser;
import com.openclassrooms.biblioback.ws.test.AppUserGetAllRequest;
import com.openclassrooms.biblioback.ws.test.TestPort;
import com.openclassrooms.biblioback.ws.test.TestPortService;
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
