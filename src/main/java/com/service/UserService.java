package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    // Create new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Find user by username
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    // Find user by Id
    public User getUserById(Long id) {	    
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // List all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete user by username
    public boolean deleteUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    // Delete user by id
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Change password for a user
    public User changePassword(Long userId, String newPassword) {
        User user = getUserById(userId);
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}
