package com.openclassrooms.services;

import com.openclassrooms.entities.BookEntity;
import com.openclassrooms.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

   @Autowired
   private BookRepository bookRepository;

   public void addBook(BookEntity bookEntity) {
       bookRepository.save(bookEntity);
   }

    @Override
    public BookEntity getBookById(int id) {
        return bookRepository.findBookEntityById(id);
    }

    @Override
    public void updateBook(BookEntity book) {
        bookRepository.save(book);
    }

    public List<BookEntity> getAllBooks(){
       List<BookEntity> bookEntities = new ArrayList<>();
       bookRepository.findAll().forEach(bookEntities::add);
       return bookEntities;
   }

    public List<BookEntity> getBookByTitle(String title){
       return bookRepository.findBookEntityByTitleContainingIgnoreCase(title);
    }

}
