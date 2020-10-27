package com.tathink.univa.service;

import com.tathink.univa.domain.Question;
import com.tathink.univa.repository.QuestionRepository;

public class QuestionService {
	
	private final QuestionRepository qRepository;
	
	public QuestionService(QuestionRepository qRepository) {
		this.qRepository = qRepository;
	}
	/**
	 * 질문 등록
	 */
	public Long apply(Question question) {
		qRepository.save(question);
		return question.getId();
	}
}
