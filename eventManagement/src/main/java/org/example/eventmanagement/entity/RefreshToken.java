package org.example.eventmanagement.entity;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.eventmanagement.config.InstantAdapter;

import java.time.Instant;

@NoArgsConstructor
@Setter
@Getter
@XmlRootElement(name = "RefreshToken")
@XmlAccessorType(XmlAccessType.FIELD)
public class RefreshToken {

    @XmlElement(name = "id")
    private long id;

    @XmlElement(name = "userId")
    private long userId; // Reference to User by ID instead of embedding User object

    @XmlElement(name = "Token")
    private String token;

    @XmlElement(name = "ExpiryDate")
    @XmlJavaTypeAdapter(InstantAdapter.class)
    private Instant expiryDate;
}