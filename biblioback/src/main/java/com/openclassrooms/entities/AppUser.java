package com.openclassrooms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String name;
    @NotNull
    private String email;
    @NotNull
    @Size(min = 5)
    private String password;
    private Boolean isAdmin = false;

    @OneToMany
    @JoinColumn(name = "borrowing_id")
    private Set<Borrowing> borrowings = new HashSet<>();

    public AppUser() {
    }

    public AppUser(String firstName, String name, String email, String password, Boolean isAdmin) {
        this.firstName = firstName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
