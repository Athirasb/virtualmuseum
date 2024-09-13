package com.JavaWizard.VirtualMuseum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JavaWizard.VirtualMuseum.entity.EventRegistration;
import com.JavaWizard.VirtualMuseum.repository.EventRegistrationRepository;

import java.util.List;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository repository;

    // Save registration method
    public EventRegistration saveRegistration(EventRegistration registration) {
        return repository.save(registration);
    }

    // Fetch all registrations method
    public List<EventRegistration> getAllRegistrations() {
        return repository.findAll(); // Retrieves all registrations from the database
    }
}
