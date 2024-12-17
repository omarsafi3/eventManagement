package org.example.eventmanagement.entity.generated;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;


import java.io.IOException;

public class CategoryDeserializer extends JsonDeserializer<Category> {
    @Override
    public Category deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String idString = node.get("id").asText();
        String name = node.get("name").asText();
        if (!idString.matches("\\d+")) {
            throw new NumberFormatException("Invalid number format for id: " + idString);
        }

        Long id = Long.parseLong(idString);

        Category category = new Category();
        category.setId(id);
        category.setName(name);

        return category;
    }

}