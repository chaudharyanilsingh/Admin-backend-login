package com.testing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Questions;
import com.testing.model.UserAnswers;

public interface UserAnswersRepository extends JpaRepository<UserAnswers, Long> {
	
	public List<UserAnswers> findAllByUserName(String gmail);
	public List<UserAnswers> findAllByQuestions(Questions question);

}
