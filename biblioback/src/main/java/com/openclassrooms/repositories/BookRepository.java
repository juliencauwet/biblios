package openclassrooms.repositories;

import openclassrooms.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    List<BookEntity> findBookEntityByTitle(String title);

    BookEntity findBookEntityById(int id);
}
