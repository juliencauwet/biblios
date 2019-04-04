package com.openclassrooms.services;

import com.openclassrooms.BibliobackApplication;
import com.openclassrooms.entities.BookEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BibliobackApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class BookServiceTest {

    @Autowired
    BookService service;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addBook() {
        BookEntity book = new BookEntity();
        book.setTitle("testbook");
        book.setAuthorName("testauthor");
        service.addBook(book);

    }

    @Test
    public void getBookById() {
        BookEntity bookEntity = service.getBookById(1);
        Assert.assertEquals("La Peste", bookEntity.getTitle());


    }

    @Test
    public void updateBook() {
        BookEntity bookEntity = service.getBookById(1);
        bookEntity.setNumber(10);
        service.updateBook(bookEntity);
        Assert.assertEquals(10, service.getBookById(1).getNumber());
    }

    @Test
    public void getAllBooks() {
        Assert.assertNotNull(service.getAllBooks());
    }

    @Test
    public void getBookByTitle() {
        List<BookEntity> bookEntity = service.getBookByTitle("Candide");
        Assert.assertEquals(1, bookEntity.size());
    }
}
