//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.12 à 09:01:23 PM GMT+01:00 
//


package org.example.eventmanagement.entity.generated;


import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.xml.bind.annotation.*;
import org.example.eventmanagement.config.LocalTimeAdapter;


import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;


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
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
 *         &lt;element name="finishTime" type="{http://www.w3.org/2001/XMLSchema}time"/&gt;
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
    "description",
    "date",
    "startTime",
    "finishTime",
    "category",
    "client",
    "room",
    "registrationWrapper",
    "eventStaffWrapper",
    "eventMaterialWrapper"
})
@XmlRootElement(name = "Event")
public class Event {

    protected long id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date date;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    @JsonSerialize(using = LocalTimeAdapter.LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeAdapter.LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    protected String startTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    @JsonSerialize(using = LocalTimeAdapter.LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeAdapter.LocalTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    protected String finishTime;
    @XmlElement(required = true)
    protected Category category;
    @XmlElement(required = true)
    protected Client client;
    @XmlElement(required = true)
    protected Room room;
    @XmlElement(name = "registrationWrapper")
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
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDate() {
        return date;
    }

    /**
     * Définit la valeur de la propriété date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * Obtient la valeur de la propriété startTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Définit la valeur de la propriété startTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * Obtient la valeur de la propriété finishTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getFinishTime() {
        return finishTime;
    }

    /**
     * Définit la valeur de la propriété finishTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinishTime(String value) {
        this.finishTime = value;
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
