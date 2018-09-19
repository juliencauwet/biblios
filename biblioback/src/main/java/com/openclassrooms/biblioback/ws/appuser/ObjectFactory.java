//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2018.08.28 à 07:46:16 PM CEST 
//


package com.openclassrooms.biblioback.ws.appuser;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.openclassrooms.biblioback.ws.appuser package. 
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

    private final static QName _AppUser_QNAME = new QName("http://appuser.ws.biblioback.openclassrooms.com", "appUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.openclassrooms.biblioback.ws.appuser
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AppUser }
     * 
     */
    public AppUser createAppUser() {
        return new AppUser();
    }

    /**
     * Create an instance of {@link AppUserAddRequest }
     * 
     */
    public AppUserAddRequest createAppUserAddRequest() {
        return new AppUserAddRequest();
    }

    /**
     * Create an instance of {@link AppUserValidityCheckResponse }
     * 
     */
    public AppUserValidityCheckResponse createAppUserValidityCheckResponse() {
        return new AppUserValidityCheckResponse();
    }

    /**
     * Create an instance of {@link AppUserGetAllRequest }
     * 
     */
    public AppUserGetAllRequest createAppUserGetAllRequest() {
        return new AppUserGetAllRequest();
    }

    /**
     * Create an instance of {@link AppUserValidityCheckRequest }
     * 
     */
    public AppUserValidityCheckRequest createAppUserValidityCheckRequest() {
        return new AppUserValidityCheckRequest();
    }

    /**
     * Create an instance of {@link AppUserAddResponse }
     * 
     */
    public AppUserAddResponse createAppUserAddResponse() {
        return new AppUserAddResponse();
    }

    /**
     * Create an instance of {@link AppUserGetAllResponse }
     * 
     */
    public AppUserGetAllResponse createAppUserGetAllResponse() {
        return new AppUserGetAllResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://appuser.ws.biblioback.openclassrooms.com", name = "appUser")
    public JAXBElement<AppUser> createAppUser(AppUser value) {
        return new JAXBElement<AppUser>(_AppUser_QNAME, AppUser.class, null, value);
    }

}
