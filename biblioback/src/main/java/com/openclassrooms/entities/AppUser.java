package com.openclassrooms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "app_role_app_user",
            joinColumns = @JoinColumn(name = "app_user_id"),
            inverseJoinColumns = @JoinColumn(name = "app_role_id"))
    private List<AppRole> appRole;

    public AppUser(String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public AppUser(String firstName, String name, String email, String password, Boolean isAdmin, List<AppRole> appRoles) {
        this.firstName = firstName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.appRole = appRoles;
    }

}
