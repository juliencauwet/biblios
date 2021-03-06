package com.openclassrooms.actions;

import com.openclassrooms.biblioback.ws.test.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class AuthentificationAction extends ActionSupport implements SessionAware{

    private static final Logger log = LoggerFactory.getLogger(AuthentificationAction.class);

    TestPortService service = new TestPortService();
    TestPort testPort = service.getTestPortSoap11();

    private String firstName;
    private String name;
    private String email;
    private String password;
    private String password2;
    private String hashedPassword;
    private Boolean isAdmin;
    private Boolean isEmployee;
    private Boolean alert;

    HttpSession session = ServletActionContext.getRequest().getSession(false);
    private AppUser appUser = (AppUser)session.getAttribute("appUser");

    private SessionMap<String, Object> sessionMap;

    public String execute(){
        return SUCCESS;
    }

    public String account(){
        return SUCCESS;
    }

    public String newAppUser(){

        if (!password.equals(password2)){
            addActionError("La confirmation doit être identique au mot de passe.");
            log.info("Les mots de passe entrés ne sont pas identiques");
            return ERROR;
        }

        AppUserAddRequest request = new AppUserAddRequest();
        request.setFirstName(firstName);
        request.setName(name);
        request.setEmail(email);
        request.setPassword(password);
        request.setIsAdmin(false);
        request.setAlert(true);

        AppUser user = testPort.appUserAdd(request).getAppUser();

        if(user != null) {

            setAppUser(user);

            sessionMap.put("appUser", appUser);
            sessionMap.put("email", email);
            sessionMap.put("firstName",firstName);
            sessionMap.put("isAdmin", isAdmin);

            addActionMessage("Utilisateur enregistré avec succès");

            return SUCCESS;

        }else {
            log.info("Erreur dans les informations");
            addActionError("Veuillez entrer les informations demandées.");
            return INPUT;
        }

    }

    public String updateProfile(){
        log.info("hello");
        AppUserUpdateRequest request = new AppUserUpdateRequest();
        appUser = (AppUser) session.getAttribute("appUser");
        appUser.setAlert(alert);
        request.setUser(appUser);
        appUser =testPort.appUserUpdate(request).getUser();
        log.info("alert: " + appUser.isAlert());
        return SUCCESS;
    }

    public String checkUserValidity(){

        AppUserValidityCheckRequest request = new AppUserValidityCheckRequest();
        request.setEmail(email);
        request.setPassword(password);

        try {
            log.info(testPort.appUserValidityCheck(request).getUser().getEmail());
            setAppUser(testPort.appUserValidityCheck(request).getUser());
            log.info("L'utilisateur est bien enregistré");
        }catch (Exception e){
            log.info("pas d'utilisateur");
            addActionError("Utilisateur ou mot de passe incorrect");
            return ERROR;
        }

        if (appUser.getEmail().equals(email) && appUser.getPassword().equals(password)) {
            sessionMap.put("appUser", appUser);
            sessionMap.put("email", appUser.getEmail());
            sessionMap.put("firstName", appUser.getFirstName());
            sessionMap.put("isAdmin", appUser.isIsAdmin());
            sessionMap.put("isEmployee", appUser.isIsEmployee());

            return SUCCESS;
        }
        else
            return INPUT;
    }

    public String logout(){
        sessionMap.invalidate();
        return SUCCESS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Boolean getAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap=(SessionMap)map;
    }

    private String toHashPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashed;
    }
}
