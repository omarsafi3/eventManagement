package org.example.eventmanagement.repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.Event;
import org.springframework.stereotype.Repository;

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
            this.context = JAXBContext.newInstance(EventListWrapper.class);

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
            marshaller.marshal(new EventListWrapper(events), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving events to XML", e);
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
            EventListWrapper wrapper = (EventListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getEvents() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading events from XML. Returning empty list.");
            return new ArrayList<>();
        }
    }

    public Event findById(Long id) {
        List<Event> events = findAll();
        return events.stream()
                .filter(event -> event.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Event findByName(String name) {
        List<Event> events = findAll();
        return events.stream()
                .filter(event -> event.getTitle().equals(name))
                .findFirst()
                .orElse(null);
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
                break;
            }
        }
        saveAll(events);
    }

    public void deleteById(Long id) {
        List<Event> events = findAll();
        events.removeIf(event -> event.getId() == id);
        saveAll(events);
    }

    public void deleteByName(String name) {
        List<Event> events = findAll();
        events.removeIf(event -> event.getTitle().equals(name));
        saveAll(events);
    }

    // Wrapper class for marshalling and unmarshalling
    @XmlRootElement(name = "events")
    private static class EventListWrapper {
        private List<Event> events;

        public EventListWrapper() {
        }

        public EventListWrapper(List<Event> events) {
            this.events = events;
        }

        @XmlElement(name = "event") // Match individual item names in XML
        public List<Event> getEvents() {
            return events;
        }

        public void setEvents(List<Event> events) {
            this.events = events;
        }
    }
}