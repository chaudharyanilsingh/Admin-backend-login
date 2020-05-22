package com.testing.service;

import java.util.List;

import com.testing.model.Questions;

public interface QuestionService {
	
	public void save(Questions questions);

	public List<Questions> getAll();

	public Questions findByQuestion(String question);

}
