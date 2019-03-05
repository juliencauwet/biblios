package openclassrooms.repositories;

import openclassrooms.entities.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser,Integer>{

    AppUser findByEmail(String email);
    AppUser findById(int id);
}
