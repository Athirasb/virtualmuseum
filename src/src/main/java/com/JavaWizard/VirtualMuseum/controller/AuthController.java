package com.JavaWizard.VirtualMuseum.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JavaWizard.VirtualMuseum.dto.UserDto;
import com.JavaWizard.VirtualMuseum.entity.Contact;
import com.JavaWizard.VirtualMuseum.entity.User;
import com.JavaWizard.VirtualMuseum.service.ContactService;
import com.JavaWizard.VirtualMuseum.service.UserService;

@Controller
@RequestMapping("/")
public class AuthController {

    private final UserService userService;
    private final ContactService contactService;

    @Autowired
    public AuthController(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/form")
    public String form() {
        return "form";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }
    @GetMapping("/auth/gallery")
    public String gallery() {
        // Your logic here
        return "authGallery";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

   

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
   
    @PostMapping("/contact")
    public String handleContactForm(@ModelAttribute Contact contact) {
        contactService.save(contact); // Save the contact data
        return "redirect:/contact"; // Redirect back to the contact page
    }

    
    @GetMapping("/create_event")
    public String createEvent() {
        return "create_event"; // This will render create_event.html from the static directory
    } 
    @GetMapping("/events_form")
    public String eventsForm() {
        return "Events_form"; // This will render Events_form.html from the templates directory
    }


}
