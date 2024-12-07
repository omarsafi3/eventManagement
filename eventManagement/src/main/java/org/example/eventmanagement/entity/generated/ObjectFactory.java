//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2024.12.06 à 10:09:20 PM GMT+01:00 
//


package org.example.eventmanagement.entity.generated;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Events }
     * 
     */
    public Events createEvents() {
        return new Events();
    }

    /**
     * Create an instance of {@link Events.Event }
     * 
     */
    public Events.Event createEventsEvent() {
        return new Events.Event();
    }

    /**
     * Create an instance of {@link Events.Event.EventMaterialWrapper }
     * 
     */
    public Events.Event.EventMaterialWrapper createEventsEventEventMaterialWrapper() {
        return new Events.Event.EventMaterialWrapper();
    }

    /**
     * Create an instance of {@link Events.Event.EventStaffWrapper }
     * 
     */
    public Events.Event.EventStaffWrapper createEventsEventEventStaffWrapper() {
        return new Events.Event.EventStaffWrapper();
    }

    /**
     * Create an instance of {@link Events.Event.RegistrationWrapper }
     * 
     */
    public Events.Event.RegistrationWrapper createEventsEventRegistrationWrapper() {
        return new Events.Event.RegistrationWrapper();
    }

    /**
     * Create an instance of {@link Registration }
     * 
     */
    public Registration createRegistration() {
        return new Registration();
    }

    /**
     * Create an instance of {@link Participant }
     * 
     */
    public Participant createParticipant() {
        return new Participant();
    }

    /**
     * Create an instance of {@link StaffMember }
     * 
     */
    public StaffMember createStaffMember() {
        return new StaffMember();
    }

    /**
     * Create an instance of {@link Material }
     * 
     */
    public Material createMaterial() {
        return new Material();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link Events.Event.EventMaterialWrapper.EventMaterial }
     * 
     */
    public Events.Event.EventMaterialWrapper.EventMaterial createEventsEventEventMaterialWrapperEventMaterial() {
        return new Events.Event.EventMaterialWrapper.EventMaterial();
    }

    /**
     * Create an instance of {@link Events.Event.EventStaffWrapper.EventStaff }
     * 
     */
    public Events.Event.EventStaffWrapper.EventStaff createEventsEventEventStaffWrapperEventStaff() {
        return new Events.Event.EventStaffWrapper.EventStaff();
    }

    /**
     * Create an instance of {@link Events.Event.RegistrationWrapper.Registration }
     * 
     */
    public Events.Event.RegistrationWrapper.Registration createEventsEventRegistrationWrapperRegistration() {
        return new Events.Event.RegistrationWrapper.Registration();
    }

}
