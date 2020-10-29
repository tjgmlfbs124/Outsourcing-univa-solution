package com.tathink.univa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.domain.Question;
import com.tathink.univa.domain.QuestionState;
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
	
	/* id로 찾기 */
	public Optional<Question> findOne(int id) {
		Optional<Question> question = qRepository.findById(id);
		return question;
	}
	
	/* 모든 질문 찾기 */
	public List<Question> findAllQuestions() {
		return qRepository.findAll();
	}
	
	/* 모든 질문 갯수 찾기 */
	public int length() {
		return findAllQuestions().size();
	}
	
	/* 최근 ~개 질문 찾기 */
	public List<Question> findRecently(int amount) {
		return qRepository.findRecently(amount);
	}
	
	/* 한계 및 상태 설정 질문 찾기 */
	public List<Question> findList(int lowLimit, int highLimit, int state) {
		List<Question> questions;
		if(state>0) {
			QuestionState qstate = new QuestionState();
			qstate.setId(state);
			questions = qRepository.findLimitAndState(lowLimit, highLimit, qstate);
		} else {			
			questions = qRepository.findLimit(lowLimit, highLimit);
		}
		
		return questions;
	}
	
	
}
