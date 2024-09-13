package com.JavaWizard.VirtualMuseum.controller;

import com.JavaWizard.VirtualMuseum.entity.Artwork;
import com.JavaWizard.VirtualMuseum.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    // Display all artworks in the gallery
    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute("artworks", artworkService.getAllArtworks());
        return "gallery";
    }

    @GetMapping("/art-uploads")
    public String showUploadForm(Model model) {
        model.addAttribute("artworks", artworkService.getAllArtworks()); // Fetch all artworks
        model.addAttribute("artwork", new Artwork()); // To handle the form submission
        return "art-uploads"; // Ensure this matches your Thymeleaf template name
    }


    // Submit new artwork
    @PostMapping("/submit-art")
    public String submitArt(Artwork artwork, @RequestParam("file") MultipartFile file) {
        try {
            artworkService.saveArtwork(artwork, file);
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // Handle error scenario
        }
        return "redirect:/gallery"; // Redirect to the gallery after submission
    }

    @GetMapping("/edit-artwork/{id}")
    public String editArtworkForm(@PathVariable("id") Long id, Model model) {
        Artwork artwork = artworkService.getArtworkById(id);
        if (artwork != null) {
            model.addAttribute("artwork", artwork);
            return "edit-artwork"; // Return the edit form (edit-artwork.html)
        }
        return "error"; // Handle artwork not found
    }

    @PostMapping("/edit-artwork/{id}")
    public String updateArtwork(@PathVariable("id") Long id, Artwork updatedArtwork, @RequestParam("file") MultipartFile file) {
        try {
            artworkService.updateArtwork(id, updatedArtwork, file);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/gallery"; // Redirect to gallery after editing
    }

    // Delete an artwork
    @GetMapping("/delete-artwork/{id}")
    public String deleteArtwork(@PathVariable("id") Long id) {
        try {
            artworkService.deleteArtworkById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "redirect:/gallery"; // Redirect to the gallery after deletion
    }
}