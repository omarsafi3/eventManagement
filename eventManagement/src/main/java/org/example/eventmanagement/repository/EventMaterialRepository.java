package org.example.eventmanagement.repository;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.EventMaterial;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventMaterialRepository {

    private static final String FILE_PATH = "event_materials.xml";
    private final File file;
    private final JAXBContext context;

    public EventMaterialRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(EventMaterialListWrapper.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    private void saveAll(List<EventMaterial> eventMaterials) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new EventMaterialListWrapper(eventMaterials), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving event materials to XML", e);
        }
    }

    public void save(EventMaterial eventMaterial) {
        List<EventMaterial> eventMaterials = findAll();
        if (eventMaterials == null) {
            eventMaterials = new ArrayList<>();
        }

        long maxId = eventMaterials.stream()
                .mapToLong(EventMaterial::getId)
                .max()
                .orElse(0);
        eventMaterial.setId(maxId + 1);
        eventMaterials.add(eventMaterial);
        saveAll(eventMaterials);
    }

    public List<EventMaterial> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            EventMaterialListWrapper wrapper = (EventMaterialListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getEventMaterials() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading event materials from XML. Returning empty list.");
            return new ArrayList<>();
        }
    }

    public EventMaterial findById(Long id) {
        return findAll().stream()
                .filter(eventMaterial -> eventMaterial.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(EventMaterial eventMaterial) {
        List<EventMaterial> eventMaterials = findAll();
        for (int i = 0; i < eventMaterials.size(); i++) {
            if (eventMaterials.get(i).getId() == eventMaterial.getId()) {
                eventMaterials.set(i, eventMaterial);
                break;
            }
        }
        saveAll(eventMaterials);
    }

    public void deleteById(Long id) {
        List<EventMaterial> eventMaterials = findAll();
        eventMaterials.removeIf(eventMaterial -> eventMaterial.getId() == id);
        saveAll(eventMaterials);
    }


    @XmlRootElement(name = "eventMaterials")
    private static class EventMaterialListWrapper {
        private List<EventMaterial> eventMaterials;

        public EventMaterialListWrapper() {
        }

        public EventMaterialListWrapper(List<EventMaterial> eventMaterials) {
            this.eventMaterials = eventMaterials;
        }

        @XmlElement(name = "eventMaterial")
        public List<EventMaterial> getEventMaterials() {
            return eventMaterials;
        }

        public void setEventMaterials(List<EventMaterial> eventMaterials) {
            this.eventMaterials = eventMaterials;
        }
    }
}
