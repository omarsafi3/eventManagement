package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.Participant;
import org.example.eventmanagement.entity.generated.Registration;
import org.example.eventmanagement.service.impl.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        List<Registration> registrations = registrationService.getAllRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable long id) {
        Registration registration = registrationService.getRegistrationById(id);
        if (registration != null) {
            return new ResponseEntity<>(registration, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerParticipant(
            @RequestParam Long eventId,
            @RequestParam Participant p,
            @RequestParam double amountPaid) {

        registrationService.registerParticipant(eventId, p, amountPaid);
        return ResponseEntity.ok("Participant registered successfully!");
    }

    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        Registration createdRegistration = registrationService.createRegistration(registration);
        return new ResponseEntity<>(createdRegistration, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(
            @PathVariable long id,
            @RequestBody Registration registration) {
        registration.setId(id);
        Registration updatedRegistration = registrationService.updateRegistration(registration);
        if (updatedRegistration != null) {
            return new ResponseEntity<>(updatedRegistration, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable long id) {
        Registration existingRegistration = registrationService.getRegistrationById(id);
        if (existingRegistration != null) {
            registrationService.deleteRegistration(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}