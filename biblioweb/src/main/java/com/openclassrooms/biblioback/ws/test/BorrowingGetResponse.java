
package com.openclassrooms.biblioback.ws.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="borrowing" type="{http://test.ws.biblioback.openclassrooms.com}borrowing"/>
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
    "borrowing"
})
@XmlRootElement(name = "borrowingGetResponse")
public class BorrowingGetResponse {

    @XmlElement(required = true)
    protected Borrowing borrowing;

    /**
     * Obtient la valeur de la propriété borrowing.
     * 
     * @return
     *     possible object is
     *     {@link Borrowing }
     *     
     */
    public Borrowing getBorrowing() {
        return borrowing;
    }

    /**
     * Définit la valeur de la propriété borrowing.
     * 
     * @param value
     *     allowed object is
     *     {@link Borrowing }
     *     
     */
    public void setBorrowing(Borrowing value) {
        this.borrowing = value;
    }

}
