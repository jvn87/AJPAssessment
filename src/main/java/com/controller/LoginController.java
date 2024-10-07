package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.User;  // Import your User entity
import com.service.UserService; // Import your UserService

@Controller
public class LoginController {

    @Autowired
    private UserService userService; // Inject UserService

    // Display the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This corresponds to the login.html template
    }

    // Handle login form submission
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Simple hardcoded user validation (You should replace this with proper service calls)
        if ("user".equals(username) && "password".equals(password)) {
            return "redirect:/success"; // Redirect to success page
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login"; // Show the login page again with an error message
        }
    }

    // Display the registration page
    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register"; // This corresponds to the register.html template
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String handleRegistration(@RequestParam String username, @RequestParam String password, Model model) {
        // Here you would save the user in the database (implement logic in UserService)
        User newUser = new User(); // Create a new User object
        newUser.setUsername(username);
        newUser.setPassword(password);
        
        // Here you can add user validation logic, e.g. checking if the username already exists
        
        userService.createUser(newUser); // Save the user

        return "redirect:/login"; // Redirect to the login page after registration
    }

    // Display success page
    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // You will create this page next
    }
}


