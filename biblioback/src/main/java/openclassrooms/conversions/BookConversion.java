package openclassrooms.conversions;

import openclassrooms.biblioback.ws.test.Book;
import openclassrooms.entities.BookEntity;
import openclassrooms.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookConversion {

    @Autowired
    private BookService bookService;

    public Book bookEntityToBook(BookEntity bookEntity){
        Book book = new Book();
        book.setId(bookEntity.getId());
        book.setTitle(bookEntity.getTitle());
        book.setAuthorName(bookEntity.getAuthorName());
        book.setAuthorFirstName(bookEntity.getAuthorFirstName());
        book.setNumber(bookEntity.getNumber());
        return book;
    }

    public BookEntity bookToBookEntity(Book book){
        BookEntity bookEntity = bookService.getBookById(book.getId());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthorName(book.getAuthorName());
        bookEntity.setAuthorFirstName(book.getAuthorFirstName());
        bookEntity.setNumber(book.getNumber());
        return bookEntity;
    }
}
