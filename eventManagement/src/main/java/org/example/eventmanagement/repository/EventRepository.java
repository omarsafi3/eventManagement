package org.example.eventmanagement.repository;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.example.eventmanagement.entity.generated.Event;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    private static final String FILE_PATH = "events.xml";
    private final File file;
    private final JAXBContext context;

    public EventRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(EventWrapperList.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    private void saveAll(List<Event> events) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new EventWrapperList(events), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving events to file", e);
        }
    }

    public void save(Event event) {
        List<Event> events = findAll();
        if (events == null) {
            events = new ArrayList<>();
        }

        long maxId = events.stream()
                .mapToLong(Event::getId)
                .max()
                .orElse(0);
        event.setId(maxId + 1);

        events.add(event);
        saveAll(events);
    }

    public List<Event> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EventWrapperList wrapper = (EventWrapperList) unmarshaller.unmarshal(file);
            List<Event> events = wrapper != null ? wrapper.getEvents() : new ArrayList<>();
            return events;
        } catch (JAXBException e) {
            throw new RuntimeException("Error reading events from file", e);
        }
    }

    public Event findByName(String name) {
        List<Event> events = findAll();
        return events.stream()
                .filter(event -> event.getTitle().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Event findById(Long id) {
        List<Event> events = findAll();
        return events.stream()
                .filter(event -> event.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long id) {
        List<Event> events = findAll();
        List<Event> updatedEvents = events.stream()
                .filter(event -> event.getId() != id)
                .collect(Collectors.toList());
        saveAll(updatedEvents);
    }

    public List<Event> findByNameContaining(String keyword) {
        return findAll().stream()
                .filter(event -> event.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void update(Event event) {
        List<Event> events = findAll();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == event.getId()) {
                events.set(i, event);
                saveAll(events);
                return;
            }
        }
        throw new RuntimeException("Event with ID " + event.getId() + " not found.");
    }

    @XmlRootElement(name = "events")
    private static class EventWrapperList {
        private final List<Event> events;

        public EventWrapperList(List<Event> events) {
            this.events = events;
        }
        @XmlElement
        public List<Event> getEvents() {
            return events;
        }
    }

}
