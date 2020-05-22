package com.testing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testing.exception.ResourceNotFoundException;
import com.testing.model.Admin;
import com.testing.model.User;
import com.testing.repository.AdminRepository;
import com.testing.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AdminRepository adminRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		String role = null;
		String[] userNameAndRole = userName.split("::");
		if (userNameAndRole.length == 2) {
			userName = userNameAndRole[0];
			role = userNameAndRole[1];
		}
		assert role != null;
		switch (role) {
		case "admin":
			Admin admin = adminRepository.findByEmail(userName).orElse(null);
			if (admin == null)
				throw new UsernameNotFoundException(
						String.format("No Admin found with username '%s'.", userName));

			return AuthUserFactory.create(admin);
		default:
			User user = userRepository.findByEmail(userName).orElse(null);
			if (user == null)
				throw new UsernameNotFoundException(
						String.format("No User found with username '%s'.", userName));

			return AuthUserFactory.create(user);

		}
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		return AuthUserFactory.create(user);
	}
}