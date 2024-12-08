package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.Participant;
import org.example.eventmanagement.service.impl.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/participants")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public ResponseEntity<Participant> addParticipant(@RequestBody Participant participant) {
        Participant createdParticipant = participantService.addParticipant(participant);
        return new ResponseEntity<>(createdParticipant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        Participant participant = participantService.getParticipantById(id);
        if (participant != null) {
            return new ResponseEntity<>(participant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Participant> getParticipantByEmail(@PathVariable String email) {
        Participant participant = participantService.getParticipantByEmail(email);
        if (participant != null) {
            return new ResponseEntity<>(participant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Participant>> findByNameContaining(@RequestParam String name) {
        List<Participant> participants = participantService.findByNameContaining(name);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Participant> updateParticipant(@RequestBody Participant participant) {
        Participant updatedParticipant = participantService.updateParticipant(participant);
        return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantService.getAllParticipants();
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
}