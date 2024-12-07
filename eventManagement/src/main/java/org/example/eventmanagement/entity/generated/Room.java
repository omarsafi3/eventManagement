//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.07 à 06:24:20 PM GMT+01:00 
//


package org.example.eventmanagement.entity.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


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
 *         &lt;element name="capacity" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="hourlyRate" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "capacity",
    "hourlyRate"
})
public class Room {

    protected long id;
    protected double capacity;
    protected int hourlyRate;

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
     * Obtient la valeur de la propriété capacity.
     * 
     */
    public double getCapacity() {
        return capacity;
    }

    /**
     * Définit la valeur de la propriété capacity.
     * 
     */
    public void setCapacity(double value) {
        this.capacity = value;
    }

    /**
     * Obtient la valeur de la propriété hourlyRate.
     * 
     */
    public int getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Définit la valeur de la propriété hourlyRate.
     * 
     */
    public void setHourlyRate(int value) {
        this.hourlyRate = value;
    }

}
