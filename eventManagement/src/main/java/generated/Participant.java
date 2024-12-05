//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.05 à 07:17:42 PM GMT+01:00 
//


package generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Participant complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Participant"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="totalPaid" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Participant", propOrder = {
    "id",
    "totalPaid"
})
public class Participant {

    protected double id;
    protected double totalPaid;

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
     * Obtient la valeur de la propriété totalPaid.
     * 
     */
    public double getTotalPaid() {
        return totalPaid;
    }

    /**
     * Définit la valeur de la propriété totalPaid.
     * 
     */
    public void setTotalPaid(double value) {
        this.totalPaid = value;
    }

}
