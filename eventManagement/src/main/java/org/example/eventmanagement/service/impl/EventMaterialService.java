package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.EventMaterial;
import org.example.eventmanagement.repository.EventMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EventMaterialService {

    @Autowired
    private EventMaterialRepository eventMaterialRepository;

    public List<EventMaterial> getAllEventMaterials() {
        return eventMaterialRepository.findAll();
    }

    public EventMaterial getEventMaterialById(long id) {
        EventMaterial eventMaterial = eventMaterialRepository.findById(id);
        if (eventMaterial == null) {
            throw new NoSuchElementException("EventMaterial not found with ID: " + id);
        }
        return eventMaterial;
    }


    public EventMaterial createEventMaterial(EventMaterial eventMaterial) {
        eventMaterialRepository.save(eventMaterial);
        return eventMaterial;
    }

    public EventMaterial updateEventMaterial(EventMaterial eventMaterial) {
        EventMaterial existingEventMaterial = getEventMaterialById(eventMaterial.getId());
        eventMaterialRepository.update(eventMaterial);
        return eventMaterial;
    }

    public void deleteEventMaterialById(long id) {
        EventMaterial existingEventMaterial = getEventMaterialById(id);
        eventMaterialRepository.deleteById(id);
    }
}
