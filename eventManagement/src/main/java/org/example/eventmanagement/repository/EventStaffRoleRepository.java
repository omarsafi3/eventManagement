package org.example.eventmanagement.repository;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.EventStaffRole;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventStaffRoleRepository {
    private static final String FILE_PATH = "event_staff_roles.xml";
    private final File file;
    private final JAXBContext context;

    public EventStaffRoleRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(EventStaffRoleListWrapper.class);


            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    private void saveAll(List<EventStaffRole> eventStaffRoles) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new EventStaffRoleListWrapper(eventStaffRoles), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving event staff roles to XML", e);
        }
    }

    public void save(EventStaffRole eventStaffRole) {
        List<EventStaffRole> eventStaffRoles = findAll();
        if (eventStaffRoles == null) {
            eventStaffRoles = new ArrayList<>();
        }
        long maxId = eventStaffRoles.stream()
                .mapToLong(EventStaffRole::getId)
                .max()
                .orElse(0);
        eventStaffRole.setId(maxId + 1);

        eventStaffRoles.add(eventStaffRole);
        saveAll(eventStaffRoles);
    }

    public List<EventStaffRole> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EventStaffRoleListWrapper wrapper = (EventStaffRoleListWrapper) unmarshaller.unmarshal(file);
            return wrapper.getEventStaffRoles();
        } catch (JAXBException e) {
            throw new RuntimeException("Error reading event staff roles from XML", e);
        }
    }

    public void deleteById(long id) {
        List<EventStaffRole> eventStaffRoles = findAll();
        eventStaffRoles.removeIf(eventStaffRole -> eventStaffRole.getId() == id);
        saveAll(eventStaffRoles);
    }

    public EventStaffRole findById(long id) {
        List<EventStaffRole> eventStaffRoles = findAll();
        return eventStaffRoles.stream()
                .filter(eventStaffRole -> eventStaffRole.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public EventStaffRole findByRoleName(String roleName) {
        List<EventStaffRole> eventStaffRoles = findAll();
        return eventStaffRoles.stream()
                .filter(eventStaffRole -> eventStaffRole.getName().equalsIgnoreCase(roleName))
                .findFirst()
                .orElse(null);
    }

    public List<EventStaffRole> findByRoleNameContaining(String keyword) {
        return findAll().stream()
                .filter(eventStaffRole -> eventStaffRole.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void update(EventStaffRole eventStaffRole) {
        List<EventStaffRole> eventStaffRoles = findAll();
        for (int i = 0; i < eventStaffRoles.size(); i++) {
            if (eventStaffRoles.get(i).getId() == eventStaffRole.getId()) {
                eventStaffRoles.set(i, eventStaffRole);
                break;
            }
        }
        saveAll(eventStaffRoles);
    }

    @XmlRootElement(name = "eventStaffRoles")
    public static class EventStaffRoleListWrapper {
        private List<EventStaffRole> eventStaffRoles;

        public EventStaffRoleListWrapper() {
        }

        public EventStaffRoleListWrapper(List<EventStaffRole> eventStaffRoles) {
            this.eventStaffRoles = eventStaffRoles;
        }

        @XmlElement(name = "eventStaffRole")
        public List<EventStaffRole> getEventStaffRoles() {
            return eventStaffRoles;
        }

        public void setEventStaffRoles(List<EventStaffRole> eventStaffRoles) {
            this.eventStaffRoles = eventStaffRoles;
        }
    }
}
