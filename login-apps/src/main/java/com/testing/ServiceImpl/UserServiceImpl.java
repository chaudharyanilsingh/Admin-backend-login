package com.testing.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.model.Admin;
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
	public User saveUser(User user) {
		return userRepository.save(user);

	}

	@Override
	public Optional<User> findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
