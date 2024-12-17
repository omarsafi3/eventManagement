package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.EventStaffRole;
import org.example.eventmanagement.repository.EventStaffRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventStaffRoleService {

    @Autowired
    private EventStaffRoleRepository eventStaffRoleRepository;

    public List<EventStaffRole> getAllEventStaffRoles() {
        return eventStaffRoleRepository.findAll();
    }

    public EventStaffRole getEventStaffRoleById(long id) {
        EventStaffRole eventStaffRole = eventStaffRoleRepository.findById(id);
        if (eventStaffRole == null) {
            throw new NoSuchElementException("EventStaffRole not found with ID: " + id);
        }
        return eventStaffRole;
    }

    public EventStaffRole getEventStaffRoleByRoleName(String roleName) {
        EventStaffRole eventStaffRole = eventStaffRoleRepository.findByRoleName(roleName);
        if (eventStaffRole == null) {
            throw new NoSuchElementException("EventStaffRole not found with role name: " + roleName);
        }
        return eventStaffRole;
    }

    public List<EventStaffRole> searchEventStaffRolesByRoleName(String keyword) {
        return eventStaffRoleRepository.findByRoleNameContaining(keyword);
    }

    public EventStaffRole createEventStaffRole(EventStaffRole eventStaffRole) {
        eventStaffRoleRepository.save(eventStaffRole);
        return eventStaffRole;
    }

    public EventStaffRole updateEventStaffRole(EventStaffRole eventStaffRole) {
        EventStaffRole existingEventStaffRole = getEventStaffRoleById(eventStaffRole.getId());
        eventStaffRoleRepository.update(eventStaffRole);
        return eventStaffRole;
    }

    public void deleteEventStaffRoleById(long id) {
        EventStaffRole existingEventStaffRole = getEventStaffRoleById(id);
        eventStaffRoleRepository.deleteById(id);
    }
}
