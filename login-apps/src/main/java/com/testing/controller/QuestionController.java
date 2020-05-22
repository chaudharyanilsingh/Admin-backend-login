package com.testing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.model.Questions;
import com.testing.service.QuestionService;
@RestController
@RequestMapping("questions")
public class QuestionController {
	

	@Autowired
	private QuestionService questionService;
	
	@PostMapping(value="/add")
	public void saveQuestion(@RequestBody Questions questions) {
		questionService.save(questions);
		
	}
	@GetMapping(value="/show/all")
	public List<Questions> getAllQuestion(){
		return questionService.getAll();
		
	}

}
