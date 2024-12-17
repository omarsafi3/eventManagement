package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.EventStaffRole;
import org.example.eventmanagement.service.impl.EventStaffRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/event-staff-roles")
public class EventStaffRoleController {

    @Autowired
    private EventStaffRoleService eventStaffRoleService;

    @GetMapping
    public ResponseEntity<List<EventStaffRole>> getAllEventStaffRoles() {
        List<EventStaffRole> eventStaffRoles = eventStaffRoleService.getAllEventStaffRoles();
        return new ResponseEntity<>(eventStaffRoles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventStaffRole> getEventStaffRoleById(@PathVariable long id) {
        try {
            EventStaffRole eventStaffRole = eventStaffRoleService.getEventStaffRoleById(id);
            return new ResponseEntity<>(eventStaffRole, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/role/{roleName}")
    public ResponseEntity<EventStaffRole> getEventStaffRoleByRoleName(@PathVariable String roleName) {
        try {
            EventStaffRole eventStaffRole = eventStaffRoleService.getEventStaffRoleByRoleName(roleName);
            return new ResponseEntity<>(eventStaffRole, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<EventStaffRole>> searchEventStaffRolesByRoleName(@RequestParam String keyword) {
        List<EventStaffRole> eventStaffRoles = eventStaffRoleService.searchEventStaffRolesByRoleName(keyword);
        return new ResponseEntity<>(eventStaffRoles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EventStaffRole> createEventStaffRole(@RequestBody EventStaffRole eventStaffRole) {
        EventStaffRole createdEventStaffRole = eventStaffRoleService.createEventStaffRole(eventStaffRole);
        return new ResponseEntity<>(createdEventStaffRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventStaffRole> updateEventStaffRole(@PathVariable long id, @RequestBody EventStaffRole eventStaffRole) {
        eventStaffRole.setId(id);
        try {
            EventStaffRole updatedEventStaffRole = eventStaffRoleService.updateEventStaffRole(eventStaffRole);
            return new ResponseEntity<>(updatedEventStaffRole, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventStaffRoleById(@PathVariable long id) {
        try {
            eventStaffRoleService.deleteEventStaffRoleById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
