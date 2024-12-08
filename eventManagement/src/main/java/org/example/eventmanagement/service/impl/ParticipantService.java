package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Participant;
import org.example.eventmanagement.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    @Autowired
    public ParticipantRepository participantRepository;

    public Participant addParticipant(Participant participant) {
        return participantRepository.addParticipant(participant);
    }

    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant getParticipantByEmail(String email) {
        return participantRepository.findByEmail(email);
    }

    public List<Participant> findByNameContaining(String name) {
        return participantRepository.findByNameContaining(name);
    }

    public Participant updateParticipant(Participant participant) {
        return participantRepository.updateParticipant(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteParticipant(id);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

}
