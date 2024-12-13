package org.example.eventmanagement.entity.generated;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class RegistrationDeserializer extends JsonDeserializer<Registration> {

    @Override
    public Registration deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String id = p.getText(); // Extract the ID from the JSON
        Registration registration = new Registration();
        registration.setId(Long.parseLong(id)); // Parse the ID and set it on the Registration object
        return registration;
    }
}
