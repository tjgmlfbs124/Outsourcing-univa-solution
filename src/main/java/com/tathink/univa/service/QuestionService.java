package com.tathink.univa.service;

import java.util.Optional;

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
	public int apply(Question question) {
		qRepository.save(question);
		return question.getId();
	}
	
	public Optional<Question> findOne(int id) {
		Optional<Question> question = qRepository.findById(id);
		return question;
	}
}
