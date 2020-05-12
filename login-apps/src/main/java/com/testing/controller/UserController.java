package com.testing.controller;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.exception.ResourceNotFoundException;
import com.testing.model.Admin;
import com.testing.model.User;
import com.testing.repository.AdminRepository;
import com.testing.repository.UserRepository;
import com.testing.security.CurrentUser;
import com.testing.security.UserPrincipal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
    @GetMapping("all")
    public List<User> getAllUsers(){
     List<User> user=userRepository.findAll();
     return user;
    	
    }
}