package com.openclassrooms.repositories;

import com.openclassrooms.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

    AppRole getById(int id);
}
