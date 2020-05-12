package com.testing.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.model.User;
import com.testing.repository.UserRepository;
import com.testing.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserById(Long id) {
		
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
		
	}
	


}
