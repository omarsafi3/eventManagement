package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Client;
import org.example.eventmanagement.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(long id) {
        Client client = clientRepository.findById(id);
        if (client == null) {
            throw new NoSuchElementException("Client not found with ID: " + id);
        }
        return client;
    }

    public Client getClientByName(String name) {
        Client client = clientRepository.findByName(name);
        if (client == null) {
            throw new NoSuchElementException("Client not found with name: " + name);
        }
        return client;
    }

    public List<Client> searchClientsByName(String keyword) {
        return clientRepository.findByNameContaining(keyword);
    }

    public Client createClient(Client client) {
        clientRepository.addClient(client);
        return client;
    }

    public Client updateClient(Client client) {
        Client existingClient = getClientById(client.getId());
        return clientRepository.updateClient(client);
    }

    public void deleteClientById(long id) {
        Client existingClient = getClientById(id);
        clientRepository.deleteClient(id);
    }
}