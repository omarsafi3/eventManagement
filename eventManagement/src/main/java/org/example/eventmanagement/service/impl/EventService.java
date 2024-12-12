package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Event;
import org.example.eventmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

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



}
