package com.testing.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.model.UserAnswers;
import com.testing.repository.UserAnswersRepository;
import com.testing.service.UserAnswerService;
@Service
public class UserAnswerServiceImpl implements UserAnswerService {
	
	@Autowired
	private UserAnswersRepository userAnswerRepository;

	@Override
	public void save(UserAnswers userAnswer) {
		userAnswerRepository.save(userAnswer);
		
	}

}
