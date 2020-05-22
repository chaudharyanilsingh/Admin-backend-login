package com.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.testing.enums.AllEnums.AuthProvider;
import com.testing.enums.AllEnums.Role;
import com.testing.exception.BadRequestException;
import com.testing.exception.OAuth2AuthenticationProcessingException;
import com.testing.model.Admin;
import com.testing.model.ApiResponse;
import com.testing.model.AuthResponse;
import com.testing.model.LoginRequest;
import com.testing.model.SignUpRequest;
import com.testing.model.User;
import com.testing.security.AuthUserFactory;
import com.testing.security.TokenProvider;
import com.testing.service.AdminService;
import com.testing.service.UserService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private AdminService adminService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail() + "::" + "user", loginRequest.getPassword()));

		User user = userService.findByEmail(loginRequest.getEmail()).orElse(null);
		if (!user.getStatus()) {
			throw new OAuth2AuthenticationProcessingException("Sorry But you are not allowed By admin");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = AuthUserFactory.create(user);

		String token = tokenProvider.generateToken(userDetails, "user");
		return ResponseEntity.ok(new AuthResponse(token));

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userService.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email address already in use.");
		}

		User user = new User();
		user.setName(signUpRequest.getName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(signUpRequest.getPassword());
		user.setProvider(AuthProvider.local);
		user.setStatus(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.user);

		User result = userService.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully@"));
	}

	@PostMapping("/admin/login")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("inside auth admin login");

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail() + "::" + "admin", loginRequest.getPassword()));

		Admin admin = adminService.findByEmail(loginRequest.getEmail()).orElse(null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = AuthUserFactory.create(admin);

		String token = tokenProvider.generateToken(userDetails, "admin");
		return ResponseEntity.ok(new AuthResponse(token));

	}

	@PostMapping("/admin/signup")
	public ResponseEntity<?> registerAdmin(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (adminService.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email address already in use.");
		}

		Admin admin = new Admin();
		admin.setName(signUpRequest.getName());
		admin.setEmail(signUpRequest.getEmail());
		admin.setPassword(signUpRequest.getPassword());
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setRole(Role.admin);

		Admin result = adminService.save(admin);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/admin/home")
				.buildAndExpand(result.getId()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Admin registered successfully@"));
	}

}