package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.EventMaterial;
import org.example.eventmanagement.service.impl.EventMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/event-materials")
public class EventMaterialController {

    @Autowired
    private EventMaterialService eventMaterialService;

    @GetMapping
    public ResponseEntity<List<EventMaterial>> getAllEventMaterials() {
        List<EventMaterial> eventMaterials = eventMaterialService.getAllEventMaterials();
        return new ResponseEntity<>(eventMaterials, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventMaterial> getEventMaterialById(@PathVariable long id) {
        try {
            EventMaterial eventMaterial = eventMaterialService.getEventMaterialById(id);
            return new ResponseEntity<>(eventMaterial, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EventMaterial> createEventMaterial(@RequestBody EventMaterial eventMaterial) {
        EventMaterial createdEventMaterial = eventMaterialService.createEventMaterial(eventMaterial);
        return new ResponseEntity<>(createdEventMaterial, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventMaterial> updateEventMaterial(@PathVariable long id, @RequestBody EventMaterial eventMaterial) {
        eventMaterial.setId(id);
        try {
            EventMaterial updatedEventMaterial = eventMaterialService.updateEventMaterial(eventMaterial);
            return new ResponseEntity<>(updatedEventMaterial, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventMaterialById(@PathVariable long id) {
        try {
            eventMaterialService.deleteEventMaterialById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
