package com.tathink.univa.service;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.domain.Question;
import com.tathink.univa.repository.QuestionRepository;

@Transactional
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
