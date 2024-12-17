package org.example.eventmanagement.repository;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.Registration;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RegistrationRepository {

    private static final String FILE_PATH = "registrations.xml";
    private final File file;
    private final JAXBContext context;

    public RegistrationRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(RegistrationListWrapper.class);

            // Initialize the file if it doesn't exist
            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    public void saveAll(List<Registration> registrations) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new RegistrationListWrapper(registrations), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving registrations to file", e);
        }
    }

    public void save(Registration registration) {
        List<Registration> registrations = findAll();
        if (registrations == null) {
            registrations = new ArrayList<>();
        }

        long maxId = registrations.stream()
                .mapToLong(Registration::getId)
                .max()
                .orElse(0);
        registration.setId(maxId + 1);

        registrations.add(registration);
        saveAll(registrations);
    }

    public List<Registration> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            RegistrationListWrapper wrapper = (RegistrationListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getRegistrations() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading registrations from file");
            return new ArrayList<>();
        }
    }

    public Registration findById(long id) {
        return findAll().stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public Registration addRegistration(Registration registration) {
        save(registration);
        return registration;
    }

    public Registration updateRegistration(Registration registration) {
        List<Registration> registrations = findAll();
        for (int i = 0; i < registrations.size(); i++) {
            if (Objects.equals(registrations.get(i).getId(), registration.getId())) {
                registrations.set(i, registration);
                saveAll(registrations);
                return registration;
            }
        }
        return null;
    }

    public void deleteRegistration(long id) {
        List<Registration> registrations = findAll();
        registrations.removeIf(r -> r.getId() == id);
        saveAll(registrations);
    }

    @XmlRootElement(name = "registrations")
    public static class RegistrationListWrapper {
        private List<Registration> registrations;

        public RegistrationListWrapper() {
        }

        public RegistrationListWrapper(List<Registration> registrations) {
            this.registrations = registrations;
        }

        @XmlElement(name = "registration")
        public List<Registration> getRegistrations() {
            return registrations;
        }

        public void setRegistrations(List<Registration> registrations) {
            this.registrations = registrations;
        }
    }
}
