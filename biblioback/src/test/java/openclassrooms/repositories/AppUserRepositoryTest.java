package openclassrooms.repositories;

import com.openclassrooms.entities.AppUser;
import com.openclassrooms.repositories.AppUserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;

    public void test(){
        AppUser user = new AppUser();
        user.setEmail("test@test.com");
        appUserRepository.save(user);
    }
}
