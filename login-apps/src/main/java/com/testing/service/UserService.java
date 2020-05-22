package com.testing.service;

import java.util.List;
import java.util.Optional;

import com.testing.model.User;

public interface UserService {
	
	public User getUserById(Long id);
	
	public User saveUser(User user);

	public Optional<User> findByEmail(String email);

	public boolean existsByEmail(String email);

	public List<User> findAll();

}
