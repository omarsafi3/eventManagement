//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.06 à 10:09:20 PM GMT+01:00 
//


package org.example.eventmanagement.entity.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="event" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="category" type="{}Category"/&gt;
 *                   &lt;element name="room" type="{}Room"/&gt;
 *                   &lt;element name="registrationWrapper" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="registration" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                                       &lt;element name="participant" type="{}Participant"/&gt;
 *                                       &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="eventStaffWrapper" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="eventStaff" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                                       &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                                       &lt;element name="staffMember" type="{}StaffMember"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="eventMaterialWrapper" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="eventMaterial" maxOccurs="unbounded"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *                                       &lt;element name="quantityUsed" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *                                       &lt;element name="material" type="{}Material"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "event"
})
@XmlRootElement(name = "events")
public class Events {

    @XmlElement(required = true)
    protected List<Events.Event> event;

    /**
     * Gets the value of the event property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the event property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Events.Event }
     * 
     * 
     */
    public List<Events.Event> getEvent() {
        if (event == null) {
            event = new ArrayList<Events.Event>();
        }
        return this.event;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="category" type="{}Category"/&gt;
     *         &lt;element name="room" type="{}Room"/&gt;
     *         &lt;element name="registrationWrapper" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="registration" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *                             &lt;element name="participant" type="{}Participant"/&gt;
     *                             &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="eventStaffWrapper" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="eventStaff" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *                             &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                             &lt;element name="staffMember" type="{}StaffMember"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="eventMaterialWrapper" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="eventMaterial" maxOccurs="unbounded"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
     *                             &lt;element name="quantityUsed" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
     *                             &lt;element name="material" type="{}Material"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/restriction&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "id",
        "title",
        "category",
        "room",
        "registrationWrapper",
        "eventStaffWrapper",
        "eventMaterialWrapper"
    })
    public static class Event {

        protected long id;
        @XmlElement(required = true)
        protected String title;
        @XmlElement(required = true)
        protected Category category;
        @XmlElement(required = true)
        protected Room room;
        protected Events.Event.RegistrationWrapper registrationWrapper;
        protected Events.Event.EventStaffWrapper eventStaffWrapper;
        protected Events.Event.EventMaterialWrapper eventMaterialWrapper;

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
         *     {@link Events.Event.RegistrationWrapper }
         *     
         */
        public Events.Event.RegistrationWrapper getRegistrationWrapper() {
            return registrationWrapper;
        }

        /**
         * Définit la valeur de la propriété registrationWrapper.
         * 
         * @param value
         *     allowed object is
         *     {@link Events.Event.RegistrationWrapper }
         *     
         */
        public void setRegistrationWrapper(Events.Event.RegistrationWrapper value) {
            this.registrationWrapper = value;
        }

        /**
         * Obtient la valeur de la propriété eventStaffWrapper.
         * 
         * @return
         *     possible object is
         *     {@link Events.Event.EventStaffWrapper }
         *     
         */
        public Events.Event.EventStaffWrapper getEventStaffWrapper() {
            return eventStaffWrapper;
        }

        /**
         * Définit la valeur de la propriété eventStaffWrapper.
         * 
         * @param value
         *     allowed object is
         *     {@link Events.Event.EventStaffWrapper }
         *     
         */
        public void setEventStaffWrapper(Events.Event.EventStaffWrapper value) {
            this.eventStaffWrapper = value;
        }

        /**
         * Obtient la valeur de la propriété eventMaterialWrapper.
         * 
         * @return
         *     possible object is
         *     {@link Events.Event.EventMaterialWrapper }
         *     
         */
        public Events.Event.EventMaterialWrapper getEventMaterialWrapper() {
            return eventMaterialWrapper;
        }

        /**
         * Définit la valeur de la propriété eventMaterialWrapper.
         * 
         * @param value
         *     allowed object is
         *     {@link Events.Event.EventMaterialWrapper }
         *     
         */
        public void setEventMaterialWrapper(Events.Event.EventMaterialWrapper value) {
            this.eventMaterialWrapper = value;
        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="eventMaterial" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
         *                   &lt;element name="quantityUsed" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
         *                   &lt;element name="material" type="{}Material"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "eventMaterial"
        })
        public static class EventMaterialWrapper {

            @XmlElement(required = true)
            protected List<Events.Event.EventMaterialWrapper.EventMaterial> eventMaterial;

            /**
             * Gets the value of the eventMaterial property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the eventMaterial property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getEventMaterial().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Events.Event.EventMaterialWrapper.EventMaterial }
             * 
             * 
             */
            public List<Events.Event.EventMaterialWrapper.EventMaterial> getEventMaterial() {
                if (eventMaterial == null) {
                    eventMaterial = new ArrayList<Events.Event.EventMaterialWrapper.EventMaterial>();
                }
                return this.eventMaterial;
            }


            /**
             * <p>Classe Java pour anonymous complex type.
             * 
             * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
             *         &lt;element name="quantityUsed" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
             *         &lt;element name="material" type="{}Material"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "id",
                "quantityUsed",
                "material"
            })
            public static class EventMaterial {

                protected long id;
                @XmlElement(required = true)
                protected BigInteger quantityUsed;
                @XmlElement(required = true)
                protected Material material;

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
                 * Obtient la valeur de la propriété quantityUsed.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getQuantityUsed() {
                    return quantityUsed;
                }

                /**
                 * Définit la valeur de la propriété quantityUsed.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setQuantityUsed(BigInteger value) {
                    this.quantityUsed = value;
                }

                /**
                 * Obtient la valeur de la propriété material.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Material }
                 *     
                 */
                public Material getMaterial() {
                    return material;
                }

                /**
                 * Définit la valeur de la propriété material.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Material }
                 *     
                 */
                public void setMaterial(Material value) {
                    this.material = value;
                }

            }

        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="eventStaff" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
         *                   &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *                   &lt;element name="staffMember" type="{}StaffMember"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "eventStaff"
        })
        public static class EventStaffWrapper {

            @XmlElement(required = true)
            protected List<Events.Event.EventStaffWrapper.EventStaff> eventStaff;

            /**
             * Gets the value of the eventStaff property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the eventStaff property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getEventStaff().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Events.Event.EventStaffWrapper.EventStaff }
             * 
             * 
             */
            public List<Events.Event.EventStaffWrapper.EventStaff> getEventStaff() {
                if (eventStaff == null) {
                    eventStaff = new ArrayList<Events.Event.EventStaffWrapper.EventStaff>();
                }
                return this.eventStaff;
            }


            /**
             * <p>Classe Java pour anonymous complex type.
             * 
             * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
             *         &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
             *         &lt;element name="staffMember" type="{}StaffMember"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "id",
                "role",
                "staffMember"
            })
            public static class EventStaff {

                protected long id;
                @XmlElement(required = true)
                protected String role;
                @XmlElement(required = true)
                protected StaffMember staffMember;

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
                 * Obtient la valeur de la propriété role.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRole() {
                    return role;
                }

                /**
                 * Définit la valeur de la propriété role.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRole(String value) {
                    this.role = value;
                }

                /**
                 * Obtient la valeur de la propriété staffMember.
                 * 
                 * @return
                 *     possible object is
                 *     {@link StaffMember }
                 *     
                 */
                public StaffMember getStaffMember() {
                    return staffMember;
                }

                /**
                 * Définit la valeur de la propriété staffMember.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link StaffMember }
                 *     
                 */
                public void setStaffMember(StaffMember value) {
                    this.staffMember = value;
                }

            }

        }


        /**
         * <p>Classe Java pour anonymous complex type.
         * 
         * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="registration" maxOccurs="unbounded"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
         *                   &lt;element name="participant" type="{}Participant"/&gt;
         *                   &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/restriction&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "registration"
        })
        public static class RegistrationWrapper {

            @XmlElement(required = true)
            protected List<Events.Event.RegistrationWrapper.Registration> registration;

            /**
             * Gets the value of the registration property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the Jakarta XML Binding object.
             * This is why there is not a <CODE>set</CODE> method for the registration property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getRegistration().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Events.Event.RegistrationWrapper.Registration }
             * 
             * 
             */
            public List<Events.Event.RegistrationWrapper.Registration> getRegistration() {
                if (registration == null) {
                    registration = new ArrayList<Events.Event.RegistrationWrapper.Registration>();
                }
                return this.registration;
            }


            /**
             * <p>Classe Java pour anonymous complex type.
             * 
             * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
             *       &lt;sequence&gt;
             *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
             *         &lt;element name="participant" type="{}Participant"/&gt;
             *         &lt;element name="amountPaid" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/restriction&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "id",
                "participant",
                "amountPaid"
            })
            public static class Registration {

                protected long id;
                @XmlElement(required = true)
                protected Participant participant;
                protected double amountPaid;

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
                 * Obtient la valeur de la propriété participant.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Participant }
                 *     
                 */
                public Participant getParticipant() {
                    return participant;
                }

                /**
                 * Définit la valeur de la propriété participant.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Participant }
                 *     
                 */
                public void setParticipant(Participant value) {
                    this.participant = value;
                }

                /**
                 * Obtient la valeur de la propriété amountPaid.
                 * 
                 */
                public double getAmountPaid() {
                    return amountPaid;
                }

                /**
                 * Définit la valeur de la propriété amountPaid.
                 * 
                 */
                public void setAmountPaid(double value) {
                    this.amountPaid = value;
                }

            }

        }

    }

}
