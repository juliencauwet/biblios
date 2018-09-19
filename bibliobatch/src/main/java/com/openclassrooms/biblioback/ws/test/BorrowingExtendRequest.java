
package com.openclassrooms.biblioback.ws.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="borrowingId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="extensionTime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "borrowingId",
    "extensionTime"
})
@XmlRootElement(name = "borrowingExtendRequest")
public class BorrowingExtendRequest {

    protected int borrowingId;
    protected int extensionTime;

    /**
     * Obtient la valeur de la propriété borrowingId.
     * 
     */
    public int getBorrowingId() {
        return borrowingId;
    }

    /**
     * Définit la valeur de la propriété borrowingId.
     * 
     */
    public void setBorrowingId(int value) {
        this.borrowingId = value;
    }

    /**
     * Obtient la valeur de la propriété extensionTime.
     * 
     */
    public int getExtensionTime() {
        return extensionTime;
    }

    /**
     * Définit la valeur de la propriété extensionTime.
     * 
     */
    public void setExtensionTime(int value) {
        this.extensionTime = value;
    }

}
