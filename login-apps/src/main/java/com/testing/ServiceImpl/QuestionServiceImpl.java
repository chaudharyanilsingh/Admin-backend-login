package com.testing.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testing.model.Questions;
import com.testing.repository.QuestionsRepository;
import com.testing.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionsRepository questionsRepository;

	@Override
	public void save(Questions questions) {
	questionsRepository.save(questions);
		
		
	}

	@Override
	public List<Questions> getAll() {
		return questionsRepository.findAll();
	}

	@Override
	public Questions findByQuestion(String questionname) {
		Questions question=questionsRepository.findByQuestion(questionname);
		return question;
	}

}
