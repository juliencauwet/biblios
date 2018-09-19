package com.openclassrooms.services;

import com.openclassrooms.entities.BookEntity;

import java.util.List;

public interface IBookService {

        List<BookEntity> getAllBooks();
        List<BookEntity> getBookByTitle(String title);
        void addBook(BookEntity bookEntity);
        BookEntity getBookById(int id);
        void updateBook(BookEntity book);

}
