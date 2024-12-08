package org.example.eventmanagement.repository;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.Material;
import org.example.eventmanagement.entity.generated.Participant;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ParticipantRepository {

    private static final String FILE_PATH = "participants.xml";
    private final File file;
    private final JAXBContext context;

    public ParticipantRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(ParticipantListWrapper.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    public void saveAll(List<Participant> participants) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new ParticipantListWrapper(participants), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving participants to file", e);
        }
    }

    private void save(Participant participant) {
        List<Participant> participants = findAll();
        if (participants == null){
            participants = new ArrayList<>();
        }

        long maxId = participants.stream()
                .mapToLong(Participant::getId)
                .max()
                .orElse(0);
        participant.setId(maxId + 1);

        participants.add(participant);
        saveAll(participants);
    }

    public List<Participant> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ParticipantListWrapper wrapper = (ParticipantListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getParticipants() : new ArrayList<>();
        }
        catch (JAXBException e) {
            System.out.println("Error reading participants from file");
            return new ArrayList<>();
        }
    }

    public Participant findById(long id) {
        return findAll().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Participant findByName(String name) {
        return findAll().stream().filter(p -> p.getFullName().equals(name)).findFirst().orElse(null);
    }

    public Participant findByEmail(String email) {
        return findAll().stream().filter(p -> p.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<Participant> findByNameContaining(String keyword) {
        return findAll().stream().filter(p -> p.getFullName().contains(keyword)).toList();
    }

    public Participant addParticipant(Participant participant) {
        save(participant);
        return participant;
    }

    public Participant updateParticipant(Participant participant) {
        List<Participant> participants = findAll();
        for (int i = 0; i < participants.size(); i++) {
            if (Objects.equals(participants.get(i).getId(), participant.getId())) {
                participants.set(i, participant);
                saveAll(participants);
                return participant;
            }
        }
        return null;
    }

    public void deleteParticipant(long id) {
        List<Participant> participants = findAll();
        participants.removeIf(p -> p.getId() == id);
        saveAll(participants);
    }

    @XmlRootElement(name="participants")
    public static class ParticipantListWrapper{
        private List<Participant> participants;
        public ParticipantListWrapper() {
        }
        public ParticipantListWrapper(List<Participant> participants) {
            this.participants = participants;
        }

        @XmlElement(name = "participant")
        public List<Participant> getParticipants() {
            return participants;
        }

        public void setParticipants(List<Participant> participants) {
            this.participants = participants;
        }
    }
}
