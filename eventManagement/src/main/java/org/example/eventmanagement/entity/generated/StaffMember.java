//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.12 à 09:01:23 PM GMT+01:00 
//


package org.example.eventmanagement.entity.generated;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.ToString;
import org.example.eventmanagement.entity.User;


/**
 * <p>Classe Java pour StaffMember complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="StaffMember"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="scheduleWrapper" type="{}ScheduleWrapper"/&gt;
 *         &lt;element name="staffSkillWrapper" type="{}StaffSkillWrapper"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaffMember", propOrder = {
    "scheduleWrapper",
    "staffSkillWrapper"
})
@ToString
public class StaffMember extends User {

    @XmlElement(required = false)
    protected ScheduleWrapper scheduleWrapper;
    @XmlElement(required = false)
    protected StaffSkillWrapper staffSkillWrapper;


    /**
     * Obtient la valeur de la propriété scheduleWrapper.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleWrapper }
     *     
     */
    public ScheduleWrapper getScheduleWrapper() {
        return scheduleWrapper;
    }

    /**
     * Définit la valeur de la propriété scheduleWrapper.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleWrapper }
     *     
     */
    public void setScheduleWrapper(ScheduleWrapper value) {
        this.scheduleWrapper = value;
    }

    /**
     * Obtient la valeur de la propriété staffSkillWrapper.
     * 
     * @return
     *     possible object is
     *     {@link StaffSkillWrapper }
     *     
     */
    public StaffSkillWrapper getStaffSkillWrapper() {
        return staffSkillWrapper;
    }

    /**
     * Définit la valeur de la propriété staffSkillWrapper.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffSkillWrapper }
     *     
     */
    public void setStaffSkillWrapper(StaffSkillWrapper value) {
        this.staffSkillWrapper = value;
    }

}
