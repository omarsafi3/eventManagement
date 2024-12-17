//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.12 à 09:01:23 PM GMT+01:00 
//


package org.example.eventmanagement.entity.generated;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.Objects;


/**
 * <p>Classe Java pour Room complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Room"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="capacity" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="hourlyRate" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="surface" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Room", propOrder = {
    "id",
    "name",
    "capacity",
    "hourlyRate",
    "surface"
})
@JsonDeserialize(using =  RoomDeserializer.class)
public class Room {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    protected int capacity;
    protected double hourlyRate;
    protected double surface;

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
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété capacity.
     * 
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Définit la valeur de la propriété capacity.
     * 
     */
    public void setCapacity(int value) {
        this.capacity = value;
    }

    /**
     * Obtient la valeur de la propriété hourlyRate.
     * 
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Définit la valeur de la propriété hourlyRate.
     * 
     */
    public void setHourlyRate(double value) {
        this.hourlyRate = value;
    }

    /**
     * Obtient la valeur de la propriété surface.
     * 
     */
    public double getSurface() {
        return surface;
    }

    /**
     * Définit la valeur de la propriété surface.
     * 
     */
    public void setSurface(double value) {
        this.surface = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        return id == room.id;   }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
