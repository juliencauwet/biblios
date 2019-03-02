package com.openclassrooms.repositories;

import com.openclassrooms.entities.BorrowingProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends JpaRepository<BorrowingProperty, Integer> {

    BorrowingProperty getByLibraryId(int libraryId);

}
