package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Event;
import org.example.eventmanagement.entity.generated.Participant;
import org.example.eventmanagement.entity.generated.Registration;
import org.example.eventmanagement.entity.generated.RegistrationWrapper;
import org.example.eventmanagement.repository.EventRepository;
import org.example.eventmanagement.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    @Autowired
    private EventRepository eventRepository;
    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(long id) {
        return registrationRepository.findById(id);
    }

    public Registration createRegistration(Registration registration) {
        return registrationRepository.addRegistration(registration);
    }

    public Registration updateRegistration(Registration registration) {
        return registrationRepository.updateRegistration(registration);
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteRegistration(id);
    }
    public void registerParticipant(Long eventId, Participant participant, double amountPaid) {
        // Find the event
        Event event = eventRepository.findById(eventId);
        if (event == null) {
            throw new RuntimeException("Event not found for ID: " + eventId);
        }

        // Create a new Registration
        Registration registration = new Registration();
        registration.setParticipant(participant);
        registration.setAmountPaid(amountPaid);

        // Add the registration to the event's RegistrationWrapper
        if (event.getRegistrationWrapper() == null) {
            event.setRegistrationWrapper(new RegistrationWrapper());
        }
        event.getRegistrationWrapper().addRegistration(registration);


        // Save the registration and the updated event
        registrationRepository.save(registration);
        eventRepository.save(event);
    }
}
