package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.EventStaff;
import org.example.eventmanagement.repository.EventStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventStaffService {

    @Autowired
    private EventStaffRepository eventStaffRepository;

    public List<EventStaff> getAllEventStaff() {
        return eventStaffRepository.findAll();
    }

    public EventStaff getEventStaffById(long id) {
        EventStaff eventStaff = eventStaffRepository.findById(id);
        if (eventStaff == null) {
            throw new NoSuchElementException("EventStaff not found with ID: " + id);
        }
        return eventStaff;
    }


    public EventStaff createEventStaff(EventStaff eventStaff) {
        eventStaffRepository.save(eventStaff);
        return eventStaff;
    }

    public EventStaff updateEventStaff(EventStaff eventStaff) {
        EventStaff existingEventStaff = getEventStaffById(eventStaff.getId());
        eventStaffRepository.update(eventStaff);
        return eventStaff;
    }

    public void deleteEventStaffById(long id) {
        EventStaff existingEventStaff = getEventStaffById(id);
        eventStaffRepository.deleteById(id);
    }
}