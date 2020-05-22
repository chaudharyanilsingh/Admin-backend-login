package com.testing.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.model.Admin;
import com.testing.model.Questions;
import com.testing.model.UserAnswers;
import com.testing.repository.AdminRepository;
import com.testing.repository.UserAnswersRepository;
import com.testing.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserAnswersRepository userAnswersRepository;
	@Override
	public Admin getAdminById(Long id) {
		
		return adminRepository.findById(id).orElse(null);
	}
	@Override
	public Optional<Admin> findByEmail(String email) {
		
		return adminRepository.findByEmail(email);
	}
	@Override
	public List<UserAnswers> findAllAnswersByUserName(String userGmail) {
		List<UserAnswers>userAnswers=userAnswersRepository.findAllByUserName(userGmail);
		System.out.println(userAnswers);
		return userAnswers;
	}
	
	@Override
	public List<UserAnswers> findAllAnswersByQuestion(Questions question) {
		List<UserAnswers>userAnswers=userAnswersRepository.findAllByQuestions(question);
		System.out.println(userAnswers);
		return userAnswers;
	}
	@Override
	public boolean existsByEmail(String email) {
		
		return adminRepository.existsByEmail(email);
	}
	@Override
	public Admin save(Admin admin) {
		
		return adminRepository.save(admin);
	}

}
