package org.example.eventmanagement.service.impl;

import org.example.eventmanagement.entity.generated.Registration;
import org.example.eventmanagement.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(long id) {
        return registrationRepository.findById(id);
    }

    public Registration createRegistration(Registration registration) {
        return registrationRepository.addRegistration(registration);
    }

    public Registration updateRegistration(Registration registration) {
        return registrationRepository.updateRegistration(registration);
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteRegistration(id);
    }
}
