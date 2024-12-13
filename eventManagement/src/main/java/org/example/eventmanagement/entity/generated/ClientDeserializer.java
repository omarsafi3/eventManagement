package org.example.eventmanagement.entity.generated;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ClientDeserializer extends JsonDeserializer<Client> {

    @Override
    public Client deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String id = p.getText(); // Extract the ID as text from the JSON parser
        Client client = new Client();
        client.setId(Long.parseLong(id)); // Convert the ID to a long and set it on the Client object
        return client;
    }
}
