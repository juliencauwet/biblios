//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.08.28 à 07:46:16 PM CEST 
//


package com.openclassrooms.biblioback.ws.book;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.openclassrooms.biblioback.ws.book package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Book_QNAME = new QName("http://book.ws.biblioback.openclassrooms.com", "book");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.openclassrooms.biblioback.ws.book
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link BookGetRequest }
     * 
     */
    public BookGetRequest createBookGetRequest() {
        return new BookGetRequest();
    }

    /**
     * Create an instance of {@link BookGetAllResponse }
     * 
     */
    public BookGetAllResponse createBookGetAllResponse() {
        return new BookGetAllResponse();
    }

    /**
     * Create an instance of {@link BookAddRequest }
     * 
     */
    public BookAddRequest createBookAddRequest() {
        return new BookAddRequest();
    }

    /**
     * Create an instance of {@link BookGetResponse }
     * 
     */
    public BookGetResponse createBookGetResponse() {
        return new BookGetResponse();
    }

    /**
     * Create an instance of {@link BookAddResponse }
     * 
     */
    public BookAddResponse createBookAddResponse() {
        return new BookAddResponse();
    }

    /**
     * Create an instance of {@link BookGetAllRequest }
     * 
     */
    public BookGetAllRequest createBookGetAllRequest() {
        return new BookGetAllRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Book }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://book.ws.biblioback.openclassrooms.com", name = "book")
    public JAXBElement<Book> createBook(Book value) {
        return new JAXBElement<Book>(_Book_QNAME, Book.class, null, value);
    }

}
