package openclassrooms.repositories;

import com.openclassrooms.entities.AppUser;
import com.openclassrooms.repositories.BorrowingRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BorrowingRepositoryTest {

    @Autowired
    private BorrowingRepository borrowingRepository;


    public void test(){
        AppUser appUser = new AppUser();
        appUser.setEmail("test@test.com");
        //borrowingRepository.save()
    }
}
