package com.testing.service;

import com.testing.model.User;

public interface UserService {
	
	public User getUserById(Long id);
	
	public void saveUser(User user);

}
