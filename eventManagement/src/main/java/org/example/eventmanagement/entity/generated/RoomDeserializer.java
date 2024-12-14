package org.example.eventmanagement.entity.generated;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class RoomDeserializer extends JsonDeserializer<Room> {
    @Override
    public Room deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        // Ensure the field is a valid number
        String idString = node.get("id").asText();
        if (!idString.matches("\\d+")) {
            throw new NumberFormatException("Invalid number format for id: " + idString);
        }

        Long id = Long.parseLong(idString);
        Room room = new Room();
        room.setId(id);
        room.setName(node.get("name").asText());
        room.setCapacity(node.get("capacity").asInt());
        room.setHourlyRate(node.get("hourlyRate").asDouble());
        room.setSurface(node.get("surface").asDouble());

        return room;
    }
}