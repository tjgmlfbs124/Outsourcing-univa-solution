package com.tathink.univa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tathink.univa.domain.Question;

public class QuestionRepository {
	
	public Question save(Question question) {
		
		return question;
	}
	
	public Optional<Question> findById(Long id) {
		
		return Optional.ofNullable(null);
	}
	
	public List<Question> findAll() {
		return new ArrayList<Question>();
	}
}
