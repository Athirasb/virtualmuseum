package com.JavaWizard.VirtualMuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/admin_login")
    public String loginPage() {
        return "admin_login"; // Return admin_login.html
    }

    @PostMapping("/admin_login")
    public RedirectView login(@RequestParam String username, @RequestParam String password) {
        // Simple check for credentials (username: admin, password: admin123)
        if ("admin".equals(username) && "admin123".equals(password)) {
            return new RedirectView("/admin"); // Redirect to admin page on success
        } else {
            return new RedirectView("/admin_login?error"); // Redirect to login page with error on failure
        }
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin"; // Return admin.html
    }
}
