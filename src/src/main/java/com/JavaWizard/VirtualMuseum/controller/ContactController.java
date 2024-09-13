package com.JavaWizard.VirtualMuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.JavaWizard.VirtualMuseum.dto.UserDto;
import com.JavaWizard.VirtualMuseum.entity.Contact;
import com.JavaWizard.VirtualMuseum.service.ContactService;
import com.JavaWizard.VirtualMuseum.service.UserService;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService; // Ensure this service is properly injected

    @GetMapping("/contact_data")
    public String contactData(Model model) {
        List<Contact> contacts = contactService.getAllContacts(); // Fetch all contacts
        model.addAttribute("contacts", contacts);
        return "contact_data"; // Ensure this HTML template exists
    }

    @GetMapping("/registered_data")
    public String registeredData(Model model) {
        try {
            List<UserDto> users = userService.findAllUsers(); // Fetch all users
            model.addAttribute("users", users); // Add the users to the model
            return "registered_data"; // Ensure this HTML template exists
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            return "error"; // Redirect to a generic error page or handle it differently
        }
    }
}
