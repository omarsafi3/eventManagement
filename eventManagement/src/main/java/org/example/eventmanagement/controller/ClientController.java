package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.generated.Client;
import org.example.eventmanagement.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable long id) {
        try {
            Client client = clientService.getClientById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<Client> getClientByName(@PathVariable String name) {
        try {
            Client client = clientService.getClientByName(name);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClientsByName(@RequestParam String keyword) {
        List<Client> clients = clientService.searchClientsByName(keyword);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        clientService.createClient(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client client) {
        client.setId(id);
        try {
            Client updatedClient = clientService.updateClient(client);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable long id) {
        try {
            clientService.deleteClientById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
