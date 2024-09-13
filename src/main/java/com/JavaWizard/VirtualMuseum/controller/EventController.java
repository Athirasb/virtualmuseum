package com.JavaWizard.VirtualMuseum.controller;

import com.JavaWizard.VirtualMuseum.entity.EventRegistration;
import com.JavaWizard.VirtualMuseum.service.EmailService;
import com.JavaWizard.VirtualMuseum.service.EventRegistrationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventController {

    @Autowired
    private EventRegistrationService registrationService;

    @Autowired
    private EmailService emailService;

    @RequestMapping("/register_event")
    public String showForm() {
        return "Events_form";
    }

    @PostMapping("/events_register")
    public String submitRegistration(
        @RequestParam String name,
        @RequestParam String phone,
        @RequestParam String email,
        @RequestParam String dob,
        @RequestParam String gender,
        @RequestParam String country,
        @RequestParam String address,
        Model model
    ) {
        EventRegistration registration = new EventRegistration();
        registration.setName(name);
        registration.setPhone(phone);
        registration.setEmail(email);
        registration.setDob(dob);
        registration.setGender(gender);
        registration.setCountry(country);
        registration.setAddress(address);

        try {
            registrationService.saveRegistration(registration);

            // Send confirmation email
            String subject = "Registration Successful";
            String text = "Dear " + name + ",\n\nThank you for registering for our event.\n\nBest regards,\nVirtual Museum Team";
            emailService.sendEmail(email, subject, text);

            model.addAttribute("message", "Successfully Registered! Confirmation email sent.");
        } catch (Exception e) {
            model.addAttribute("message", "Registration failed: " + e.getMessage());
        }

        return "Events_form";
    }
    @GetMapping("/event_registration_details")
    public String eventRegistrationDetails(Model model) {
        List<EventRegistration> registrations = registrationService.getAllRegistrations(); // Fetch all event registrations
        model.addAttribute("registrations", registrations); // Add the list to the model
        return "event_registration_details"; // Ensure this HTML template exists
    }
}
