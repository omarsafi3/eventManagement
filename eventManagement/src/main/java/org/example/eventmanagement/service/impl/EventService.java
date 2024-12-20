package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Event;
import org.example.eventmanagement.entity.generated.Participant;
import org.example.eventmanagement.entity.generated.Registration;
import org.example.eventmanagement.entity.generated.RegistrationWrapper;
import org.example.eventmanagement.repository.EventRepository;
import org.example.eventmanagement.security.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository repository;

    public Event addEvent(Event event) {
        repository.save(event);
        return event;
    }

    public Event findById(Long id) {
        return repository.findById(id);
    }

    public Event findByName(String name) {
        return repository.findByName(name);
    }

    public Event updateEvent(Event event) {
        repository.update(event);
        return event;
    }

    public List<Event> allEvents() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Event> findByNameContaining(String keyword) {
        return repository.findByNameContaining(keyword);
    }


    public void registerParticipant(long eventId, RegistrationRequest registrationRequest) {
        // Fetch the event from the repository
        Event event = findById(eventId);

        // Validate and set Participant
        if (registrationRequest.getParticipant() == null) {
            throw new IllegalArgumentException("Participant must be provided");
        }

        // Create a new Registration
        Registration registration = new Registration();
        registration.setParticipant(registrationRequest.getParticipant());
        registration.setAmountPaid(registrationRequest.getAmountPaid());

        // Ensure RegistrationWrapper exists
        RegistrationWrapper registrationWrapper = event.getRegistrationWrapper();
        if (registrationWrapper == null) {
            registrationWrapper = new RegistrationWrapper();
            event.setRegistrationWrapper(registrationWrapper);
        }

        // Add the registration to the wrapper
        List<Registration> registrationList = registrationWrapper.getRegistration();
        if (registrationList == null) {
            registrationList = new ArrayList<>();
            registrationWrapper.setRegistration(registrationList);
        }

        // Add new registration
        registrationList.add(registration);

        // Save the updated event
        updateEvent(event);

        log.info("Registration added to event. Total registrations: {}",
                registrationList.size());
    }







}
