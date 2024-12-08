//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.08 à 07:28:36 PM GMT+01:00 
//


package org.example.eventmanagement.entity.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Event complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Event"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="category" type="{}Category"/&gt;
 *         &lt;element name="client" type="{}Client"/&gt;
 *         &lt;element name="room" type="{}Room"/&gt;
 *         &lt;element name="registrationWrapper" type="{}RegistrationWrapper" minOccurs="0"/&gt;
 *         &lt;element name="eventStaffWrapper" type="{}EventStaffWrapper" minOccurs="0"/&gt;
 *         &lt;element name="eventMaterialWrapper" type="{}EventMaterialWrapper" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Event", propOrder = {
    "id",
    "title",
    "category",
    "client",
    "room",
    "registrationWrapper",
    "eventStaffWrapper",
    "eventMaterialWrapper"
})
public class Event {

    protected long id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected Category category;
    @XmlElement(required = true)
    protected Client client;
    @XmlElement(required = true)
    protected Room room;
    protected RegistrationWrapper registrationWrapper;
    protected EventStaffWrapper eventStaffWrapper;
    protected EventMaterialWrapper eventMaterialWrapper;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Définit la valeur de la propriété title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Obtient la valeur de la propriété category.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Définit la valeur de la propriété category.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Obtient la valeur de la propriété client.
     * 
     * @return
     *     possible object is
     *     {@link Client }
     *     
     */
    public Client getClient() {
        return client;
    }

    /**
     * Définit la valeur de la propriété client.
     * 
     * @param value
     *     allowed object is
     *     {@link Client }
     *     
     */
    public void setClient(Client value) {
        this.client = value;
    }

    /**
     * Obtient la valeur de la propriété room.
     * 
     * @return
     *     possible object is
     *     {@link Room }
     *     
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Définit la valeur de la propriété room.
     * 
     * @param value
     *     allowed object is
     *     {@link Room }
     *     
     */
    public void setRoom(Room value) {
        this.room = value;
    }

    /**
     * Obtient la valeur de la propriété registrationWrapper.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationWrapper }
     *     
     */
    public RegistrationWrapper getRegistrationWrapper() {
        return registrationWrapper;
    }

    /**
     * Définit la valeur de la propriété registrationWrapper.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationWrapper }
     *     
     */
    public void setRegistrationWrapper(RegistrationWrapper value) {
        this.registrationWrapper = value;
    }

    /**
     * Obtient la valeur de la propriété eventStaffWrapper.
     * 
     * @return
     *     possible object is
     *     {@link EventStaffWrapper }
     *     
     */
    public EventStaffWrapper getEventStaffWrapper() {
        return eventStaffWrapper;
    }

    /**
     * Définit la valeur de la propriété eventStaffWrapper.
     * 
     * @param value
     *     allowed object is
     *     {@link EventStaffWrapper }
     *     
     */
    public void setEventStaffWrapper(EventStaffWrapper value) {
        this.eventStaffWrapper = value;
    }

    /**
     * Obtient la valeur de la propriété eventMaterialWrapper.
     * 
     * @return
     *     possible object is
     *     {@link EventMaterialWrapper }
     *     
     */
    public EventMaterialWrapper getEventMaterialWrapper() {
        return eventMaterialWrapper;
    }

    /**
     * Définit la valeur de la propriété eventMaterialWrapper.
     * 
     * @param value
     *     allowed object is
     *     {@link EventMaterialWrapper }
     *     
     */
    public void setEventMaterialWrapper(EventMaterialWrapper value) {
        this.eventMaterialWrapper = value;
    }

}
