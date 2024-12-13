package org.example.eventmanagement.entity.generated;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class RoomDeserializer extends JsonDeserializer<Room> {
    @Override
    public Room deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String id = p.getText();
        Room room = new Room();
        room.setId(Long.parseLong(id));
        return room;
    }
}
