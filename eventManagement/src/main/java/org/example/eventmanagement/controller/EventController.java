package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.Event;
import org.example.eventmanagement.service.impl.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.allEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(Event event) {
        Event createdEvent = eventService.addEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

}
