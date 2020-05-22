package com.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.model.UserAnswers;
import com.testing.service.QuestionService;
import com.testing.service.UserAnswerService;

@RestController
@RequestMapping("answer")
public class AnswerController {

	@Autowired
	private UserAnswerService answerService;

	@Autowired
	private QuestionService questionService;

	@PostMapping(value = "/save")
	private void saveAnswer(@RequestBody UserAnswers userAnswer) {
		userAnswer.setQuestions(questionService.findByQuestion(userAnswer.getQuestions().getQuestion()));
		answerService.save(userAnswer);
	}

}
