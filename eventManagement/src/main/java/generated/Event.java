//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.05 à 07:17:42 PM GMT+01:00 
//


package generated;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="ticketFee" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="maxAttendees" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="category" type="{}Category"/&gt;
 *         &lt;element name="room" type="{}Room"/&gt;
 *         &lt;element name="eventMaterial" type="{}EventMaterial"/&gt;
 *         &lt;element name="registration" type="{}Registration"/&gt;
 *         &lt;element name="eventStaff" type="{}EventStaff"/&gt;
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
    "startTime",
    "endTime",
    "ticketFee",
    "maxAttendees",
    "category",
    "room",
    "eventMaterial",
    "registration",
    "eventStaff"
})
public class Event {

    protected double id;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected double ticketFee;
    protected int maxAttendees;
    @XmlElement(required = true)
    protected Category category;
    @XmlElement(required = true)
    protected Room room;
    @XmlElement(required = true)
    protected EventMaterial eventMaterial;
    @XmlElement(required = true)
    protected Registration registration;
    @XmlElement(required = true)
    protected EventStaff eventStaff;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public double getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(double value) {
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
     * Obtient la valeur de la propriété startTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
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
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Obtient la valeur de la propriété endTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Définit la valeur de la propriété endTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Obtient la valeur de la propriété ticketFee.
     * 
     */
    public double getTicketFee() {
        return ticketFee;
    }

    /**
     * Définit la valeur de la propriété ticketFee.
     * 
     */
    public void setTicketFee(double value) {
        this.ticketFee = value;
    }

    /**
     * Obtient la valeur de la propriété maxAttendees.
     * 
     */
    public int getMaxAttendees() {
        return maxAttendees;
    }

    /**
     * Définit la valeur de la propriété maxAttendees.
     * 
     */
    public void setMaxAttendees(int value) {
        this.maxAttendees = value;
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
     * Obtient la valeur de la propriété eventMaterial.
     * 
     * @return
     *     possible object is
     *     {@link EventMaterial }
     *     
     */
    public EventMaterial getEventMaterial() {
        return eventMaterial;
    }

    /**
     * Définit la valeur de la propriété eventMaterial.
     * 
     * @param value
     *     allowed object is
     *     {@link EventMaterial }
     *     
     */
    public void setEventMaterial(EventMaterial value) {
        this.eventMaterial = value;
    }

    /**
     * Obtient la valeur de la propriété registration.
     * 
     * @return
     *     possible object is
     *     {@link Registration }
     *     
     */
    public Registration getRegistration() {
        return registration;
    }

    /**
     * Définit la valeur de la propriété registration.
     * 
     * @param value
     *     allowed object is
     *     {@link Registration }
     *     
     */
    public void setRegistration(Registration value) {
        this.registration = value;
    }

    /**
     * Obtient la valeur de la propriété eventStaff.
     * 
     * @return
     *     possible object is
     *     {@link EventStaff }
     *     
     */
    public EventStaff getEventStaff() {
        return eventStaff;
    }

    /**
     * Définit la valeur de la propriété eventStaff.
     * 
     * @param value
     *     allowed object is
     *     {@link EventStaff }
     *     
     */
    public void setEventStaff(EventStaff value) {
        this.eventStaff = value;
    }

}
