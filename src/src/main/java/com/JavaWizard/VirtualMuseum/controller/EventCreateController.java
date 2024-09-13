package com.JavaWizard.VirtualMuseum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.JavaWizard.VirtualMuseum.entity.EventCreate;
import com.JavaWizard.VirtualMuseum.service.EventCreateService;

@Controller
public class EventCreateController {

    @Autowired
    private EventCreateService eventCreateService;

    @GetMapping("/events/create")
    public String showCreateEventForm(Model model) {
        model.addAttribute("event", new EventCreate());
        return "create_event"; // Ensure this matches your template name
    }

    @PostMapping("/events/create")
    public String createEvent(@ModelAttribute EventCreate event, 
                              @RequestParam("image") MultipartFile image, 
                              BindingResult result, 
                              Model model) {
        if (result.hasErrors()) {
            return "create_event"; // Return to form with errors
        }
        eventCreateService.saveEvent(event, image);
        return "redirect:/events/display";
    }

    @GetMapping("/events/display")
    public String displayEvents(Model model) {
        List<EventCreate> events = eventCreateService.getAllEvents();
        model.addAttribute("events", events);
        return "display"; // Ensure this matches your template name
    }

    @GetMapping("/events/edit")
    public String editEventForm(@RequestParam("id") Long id, Model model) {
        EventCreate event = eventCreateService.getEventById(id);
        if (event != null) {
            model.addAttribute("event", event);
            return "edit_event"; // Ensure this matches your template name
        } else {
            return "redirect:/events/display";
        }
    }

    @PostMapping("/events/update")
    public String updateEvent(@ModelAttribute EventCreate event, 
                              @RequestParam("image") MultipartFile image) {
        eventCreateService.saveEvent(event, image);
        return "redirect:/events/display";
    }

    @PostMapping("/events/delete")
    public String deleteEvent(@RequestParam("id") Long id) {
        eventCreateService.deleteEvent(id);
        return "redirect:/events/display";
    }

    @GetMapping("/events/api")
    @ResponseBody
    public List<EventCreate> getAllEvents() {
        return eventCreateService.getAllEvents(); // Fetch and return the list of events
    }
    @GetMapping("/exhibitions")
    public String showEvents(Model model) {
        List<EventCreate> events = eventCreateService.getAllEvents();
        model.addAttribute("events", events);
        return "exhibitions"; // Ensure this matches your template name
    }
}
