package openclassrooms.services;

import com.openclassrooms.BibliobackApplication;
import com.openclassrooms.entities.Borrowing;
import com.openclassrooms.repositories.BorrowingRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BibliobackApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BorrowingServiceTest {

    @Autowired
    BorrowingRepository borrowingRepository;

    @Test
    @Order(1)
    public void newBorrowing() {
        borrowingRepository.save(new Borrowing());
    }

    @Test
    public void updateBorrowing() {

    }

    @Test
    public void borrowingReport() {
    }

    @Test
    public void getById() {
        Assert.assertEquals(8, borrowingRepository.findById(14).getAppUser().getId());
    }

    @Test
    public void getByAppUserId() {
    }

    @Test
    public void getExpiredBorrowing() {
    }

    @Test
    public void getAllBorrowings() {

    }

    @Test
    public void filterBorrowingByStatus() {
        //Assert.assertEquals(new Borrowing(), borrowingRepository.findBorrowingsByStatus(Status.ONGOING));
    }

    @Test
    public void deleteBorrowingListById() {
    }

    @Test
    public void getBorrowingsByBook() {
    }
}
