package org.example.eventmanagement.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@JsonSerialize(using = TimeAdapter.TimeSerializer.class)
@JsonDeserialize(using = TimeAdapter.TimeDeserializer.class)
public class TimeAdapter {

    private static final String TIME_FORMAT = "HH:mm";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);

    public static class TimeSerializer extends JsonSerializer<Time> {
        @Override
        public void serialize(Time value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(new SimpleDateFormat(TIME_FORMAT).format(value));
        }
    }

    public static class TimeDeserializer extends JsonDeserializer<Time> {
        @Override
        public Time deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            try {
                return new Time(new SimpleDateFormat(TIME_FORMAT).parse(p.getText()).getTime());
            } catch (ParseException e) {
                throw new RuntimeException("Failed to parse time: " + p.getText(), e);
            }
        }
    }
}