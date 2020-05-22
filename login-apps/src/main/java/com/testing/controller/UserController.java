package com.testing.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.exception.ResourceNotFoundException;
import com.testing.model.User;
import com.testing.security.AuthUser;
import com.testing.security.CurrentUser;
import com.testing.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	public User getCurrentUser(@CurrentUser AuthUser authUser) {
		User user = userService.getUserById(authUser.getId());
		if (user == null) {
			throw new ResourceNotFoundException("User", "id", authUser.getId());
		}
		return user;

	}

	@GetMapping("all")
	public List<User> getAllUsers() {
		List<User> user = userService.findAll();
		return user;

	}
}