package com.JavaWizard.VirtualMuseum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaWizard.VirtualMuseum.entity.EventRegistration;
import com.JavaWizard.VirtualMuseum.repository.EventRegistrationRepository;

@Service
public class EventService {

    @Autowired
    private EventRegistrationRepository repository;

    @Autowired
    private EmailService emailService;

    public void saveRegistration(EventRegistration registration) {
        repository.save(registration);

        // Send confirmation email
        String subject = "Event Registration Confirmation";
        String text = "Dear " + registration.getName() + ",\n\n" +
                      "Thank you for registering for our event. Here are your registration details:\n" +
                      "Name: " + registration.getName() + "\n" +
                      "Phone: " + registration.getPhone() + "\n" +
                      "Email: " + registration.getEmail() + "\n" +
                      "Date of Birth: " + registration.getDob() + "\n" +
                      "Gender: " + registration.getGender() + "\n" +
                      "Country: " + registration.getCountry() + "\n" +
                      "Address: " + registration.getAddress() + "\n\n" +
                      "We look forward to seeing you at the event.\n\n" +
                      "Best regards,\n" +
                      "The Event Team";

       
    }
}
