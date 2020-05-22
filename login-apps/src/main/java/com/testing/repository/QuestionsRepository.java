package com.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testing.model.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

	public Questions findByQuestion(String name);
}
