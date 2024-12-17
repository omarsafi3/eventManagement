package org.example.eventmanagement.security;

import lombok.Getter;
import lombok.Setter;
import org.example.eventmanagement.entity.generated.Participant;
import org.example.eventmanagement.entity.generated.Registration;

@Getter
@Setter
public class RegistrationRequest extends Registration {

    private Participant participant;
    private double amountPaid;

    // Getters and setters...
}
