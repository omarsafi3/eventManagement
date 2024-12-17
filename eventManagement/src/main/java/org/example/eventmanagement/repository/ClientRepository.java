package org.example.eventmanagement.repository;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.eventmanagement.entity.generated.Client;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ClientRepository {

    private static final String FILE_PATH = "clients.xml";
    private final File file;
    private final JAXBContext context;

    public ClientRepository() {
        try {
            this.file = new File(FILE_PATH);
            this.context = JAXBContext.newInstance(ClientListWrapper.class);


            if (!file.exists()) {
                saveAll(new ArrayList<>());
            }
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing JAXB context", e);
        }
    }

    public void saveAll(List<Client> clients) {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new ClientListWrapper(clients), file);
        } catch (JAXBException e) {
            throw new RuntimeException("Error saving clients to file", e);
        }
    }

    private void save(Client client) {
        List<Client> clients = findAll();
        if (clients == null) {
            clients = new ArrayList<>();
        }

        long maxId = clients.stream()
                .mapToLong(Client::getId)
                .max()
                .orElse(0);
        client.setId(maxId + 1);

        clients.add(client);
        saveAll(clients);
    }

    public List<Client> findAll() {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ClientListWrapper wrapper = (ClientListWrapper) unmarshaller.unmarshal(file);
            return wrapper != null ? wrapper.getClients() : new ArrayList<>();
        } catch (JAXBException e) {
            System.out.println("Error reading clients from file");
            return new ArrayList<>();
        }
    }

    public Client findById(long id) {
        return findAll().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Client findByName(String name) {
        return findAll().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    public Client findByEmail(String email) {
        return findAll().stream().filter(c -> c.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<Client> findByNameContaining(String keyword) {
        return findAll().stream().filter(c -> c.getName().contains(keyword)).toList();
    }

    public Client addClient(Client client) {
        save(client);
        return client;
    }

    public Client updateClient(Client client) {
        List<Client> clients = findAll();
        for (int i = 0; i < clients.size(); i++) {
            if (Objects.equals(clients.get(i).getId(), client.getId())) {
                clients.set(i, client);
                saveAll(clients);
                return client;
            }
        }
        return null;
    }

    public void deleteClient(long id) {
        List<Client> clients = findAll();
        clients.removeIf(c -> c.getId() == id);
        saveAll(clients);
    }

    @XmlRootElement(name = "clients")
    public static class ClientListWrapper {
        private List<Client> clients;

        public ClientListWrapper() {
        }

        public ClientListWrapper(List<Client> clients) {
            this.clients = clients;
        }

        @XmlElement(name = "client")
        public List<Client> getClients() {
            return clients;
        }

        public void setClients(List<Client> clients) {
            this.clients = clients;
        }
    }
}
