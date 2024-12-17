package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.EventStaff;
import org.example.eventmanagement.service.impl.EventStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/event-staff")
public class EventStaffController {

    @Autowired
    private EventStaffService eventStaffService;

    @GetMapping
    public ResponseEntity<List<EventStaff>> getAllEventStaff() {
        List<EventStaff> eventStaffList = eventStaffService.getAllEventStaff();
        return new ResponseEntity<>(eventStaffList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventStaff> getEventStaffById(@PathVariable long id) {
        try {
            EventStaff eventStaff = eventStaffService.getEventStaffById(id);
            return new ResponseEntity<>(eventStaff, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EventStaff> createEventStaff(@RequestBody EventStaff eventStaff) {
        EventStaff createdEventStaff = eventStaffService.createEventStaff(eventStaff);
        return new ResponseEntity<>(createdEventStaff, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventStaff> updateEventStaff(@PathVariable long id, @RequestBody EventStaff eventStaff) {
        try {
            eventStaff.setId(id);  // Ensure the ID is correctly set in the update
            EventStaff updatedEventStaff = eventStaffService.updateEventStaff(eventStaff);
            return new ResponseEntity<>(updatedEventStaff, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventStaffById(@PathVariable long id) {
        try {
            eventStaffService.deleteEventStaffById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
