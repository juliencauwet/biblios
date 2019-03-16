package com.openclassrooms.repositories;

import com.openclassrooms.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer> {
    List<Email> findBySubject(String subject);
}
