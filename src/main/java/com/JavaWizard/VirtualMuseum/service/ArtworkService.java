package com.JavaWizard.VirtualMuseum.service;

import com.JavaWizard.VirtualMuseum.entity.Artwork;
import com.JavaWizard.VirtualMuseum.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ArtworkService {

    private final Path root = Paths.get("src/main/resources/static/uploads");

    @Autowired
    private ArtworkRepository repository;

    public Artwork saveArtwork(Artwork artwork, MultipartFile file) throws Exception {
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }

        String fileName = file.getOriginalFilename();
        Files.copy(file.getInputStream(), root.resolve(fileName));

        artwork.setImageName(fileName);
        return repository.save(artwork);
    }

    public List<Artwork> getAllArtworks() {
        return repository.findAll();
    }

    public Artwork getArtworkById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteArtworkById(Long id) {
        repository.deleteById(id);
    }

    // Update existing artwork
    public void updateArtwork(Long id, Artwork updatedArtwork, MultipartFile file) throws Exception {
        Artwork existingArtwork = repository.findById(id).orElse(null);
        if (existingArtwork != null) {
            // Update artwork details
            existingArtwork.setName(updatedArtwork.getName());
            existingArtwork.setEmail(updatedArtwork.getEmail());
            existingArtwork.setPhone(updatedArtwork.getPhone());
            existingArtwork.setArtTitle(updatedArtwork.getArtTitle());
            existingArtwork.setArtDescription(updatedArtwork.getArtDescription());

            // Update the image if a new one is provided
            if (file != null && !file.isEmpty()) {
                // Delete the old image if necessary
                if (existingArtwork.getImageName() != null) {
                    Path oldImagePath = root.resolve(existingArtwork.getImageName());
                    Files.deleteIfExists(oldImagePath);
                }

                // Save the new image
                String newFileName = file.getOriginalFilename();
                Files.copy(file.getInputStream(), root.resolve(newFileName));
                existingArtwork.setImageName(newFileName);
            }

            // Save the updated artwork
            repository.save(existingArtwork);
        }
    }
}
