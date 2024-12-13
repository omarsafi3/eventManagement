package org.example.eventmanagement.config;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.Instant;

public class InstantAdapter extends XmlAdapter<String, Instant> {

    @Override
    public Instant unmarshal(String v) {
        return Instant.parse(v);
    }

    @Override
    public String marshal(Instant v) {
        return v.toString();
    }
}
