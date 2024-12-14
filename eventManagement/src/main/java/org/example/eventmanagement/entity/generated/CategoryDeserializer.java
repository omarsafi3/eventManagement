package org.example.eventmanagement.entity.generated;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.eventmanagement.entity.generated.Category;

import java.io.IOException;

public class CategoryDeserializer extends JsonDeserializer<Category> {
    @Override
    public Category deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        // Ensure the field is a valid number
        String idString = node.get("id").asText();
        String name = node.get("name").asText();
        if (!idString.matches("\\d+")) {
            throw new NumberFormatException("Invalid number format for id: " + idString);
        }

        Long id = Long.parseLong(idString);
        // Other deserialization logic...
        Category category = new Category();
        category.setId(id);
        category.setName(name);

        return category;
    }

}