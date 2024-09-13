package com.JavaWizard.VirtualMuseum.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.JavaWizard.VirtualMuseum.entity.EventCreate;
import com.JavaWizard.VirtualMuseum.repository.EventCreateRepository;

@Service
public class EventCreateService {

    @Autowired
    private EventCreateRepository eventCreateRepository;

    private static final String UPLOADED_FOLDER = "src/main/resources/static/uploads/";

    public void saveEvent(EventCreate event, MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                String fileName = image.getOriginalFilename();
                Path uploadDir = Paths.get(UPLOADED_FOLDER);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir); // Create directory if not exists
                }
                Path path = uploadDir.resolve(fileName);
                Files.write(path, image.getBytes());
                event.setImageName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        eventCreateRepository.save(event);
    }


    public List<EventCreate> getAllEvents() {
        return eventCreateRepository.findAll();
    }

    public EventCreate getEventById(Long id) {
        return eventCreateRepository.findById(id).orElse(null);
    }

    public void deleteEvent(Long id) {
        eventCreateRepository.deleteById(id);
    }
}
