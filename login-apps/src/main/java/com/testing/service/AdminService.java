package com.testing.service;

import java.util.List;
import java.util.Optional;

import com.testing.model.Admin;
import com.testing.model.Questions;
import com.testing.model.User;
import com.testing.model.UserAnswers;

public interface AdminService {
	public Admin getAdminById(Long id);

	public Optional<Admin> findByEmail(String email);

	public List<UserAnswers> findAllAnswersByUserName(String userGmail);

	public List<UserAnswers> findAllAnswersByQuestion(Questions question);

	public boolean existsByEmail(String email);

	public Admin save(Admin admin);

}
