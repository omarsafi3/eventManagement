package org.example.eventmanagement.repository;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.EventStaff;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventStaffRepository {
    private static final String FILE_PATH = "event_staff.xml";
    private final File file;
    private final JAXBContext context;

    public EventStaffRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(EventStaffListWrapper.class);

            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    private void saveAll(List<EventStaff> eventStaffList) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new EventStaffListWrapper(eventStaffList), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving event staff to XML", e);
        }
    }

    public void save(EventStaff eventStaff) {
        List<EventStaff> eventStaffList = findAll();
        if (eventStaffList == null) {
            eventStaffList = new ArrayList<>();
        }
        long maxId = eventStaffList.stream()
                .mapToLong(EventStaff::getId)
                .max()
                .orElse(0);
        eventStaff.setId(maxId + 1);

        eventStaffList.add(eventStaff);
        saveAll(eventStaffList);
    }

    public List<EventStaff> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EventStaffListWrapper wrapper = (EventStaffListWrapper) unmarshaller.unmarshal(file);
            return wrapper.getEventStaffList();
        } catch (JAXBException e) {
            throw new RuntimeException("Error reading event staff from XML", e);
        }
    }

    public void deleteById(long id) {
        List<EventStaff> eventStaffList = findAll();
        eventStaffList.removeIf(eventStaff -> eventStaff.getId() == id);
        saveAll(eventStaffList);
    }

    public EventStaff findById(long id) {
        List<EventStaff> eventStaffList = findAll();
        return eventStaffList.stream()
                .filter(eventStaff -> eventStaff.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public EventStaff findByName(String name) {
        List<EventStaff> eventStaffList = findAll();
        return eventStaffList.stream()
                .filter(eventStaff -> eventStaff.getStaffMember().getFullName().toLowerCase().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<EventStaff> findByNameContaining(String keyword) {
        return findAll().stream()
                .filter(eventStaff -> eventStaff.getStaffMember().getFullName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void update(EventStaff eventStaff) {
        List<EventStaff> eventStaffList = findAll();
        for (int i = 0; i < eventStaffList.size(); i++) {
            if (eventStaffList.get(i).getId() == eventStaff.getId()) {
                eventStaffList.set(i, eventStaff);
                break;
            }
        }
        saveAll(eventStaffList);
    }

    @XmlRootElement(name = "eventStaffList")
    public static class EventStaffListWrapper {
        private List<EventStaff> eventStaffList;

        public EventStaffListWrapper() {
        }

        public EventStaffListWrapper(List<EventStaff> eventStaffList) {
            this.eventStaffList = eventStaffList;
        }

        @XmlElement(name = "eventStaff")
        public List<EventStaff> getEventStaffList() {
            return eventStaffList;
        }

        public void setEventStaffList(List<EventStaff> eventStaffList) {
            this.eventStaffList = eventStaffList;
        }
    }
}
