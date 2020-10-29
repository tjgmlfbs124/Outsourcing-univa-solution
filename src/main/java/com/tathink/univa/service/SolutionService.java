package com.tathink.univa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.domain.Solution;
import com.tathink.univa.domain.SolutionState;
import com.tathink.univa.repository.SolutionRepository;

@Transactional
public class SolutionService {
	
	private final SolutionRepository qRepository;
	
	public SolutionService(SolutionRepository qRepository) {
		this.qRepository = qRepository;
	}
	/**
	 * 질문 등록
	 */
	public int apply(Solution question) {
		qRepository.save(question);
		return question.getId();
	}
	
	/* id로 찾기 */
	public Optional<Solution> findOne(int id) {
		Optional<Solution> question = qRepository.findById(id);
		return question;
	}
	
	/* 모든 질문 찾기 */
	public List<Solution> findAllQuestions() {
		return qRepository.findAll();
	}
	
	/* 모든 질문 갯수 찾기 */
	public int length() {
		return findAllQuestions().size();
	}
	
	/* 최근 ~개 질문 찾기 */
	public List<Solution> findRecently(int amount) {
		return qRepository.findRecently(amount);
	}
	
	/* 한계 및 상태 설정 질문 찾기 */
	public List<Solution> findList(int lowLimit, int highLimit, int state) {
		List<Solution> questions;
		if(state>0) {
			SolutionState qstate = new SolutionState();
			qstate.setId(state);
			questions = qRepository.findLimitAndState(lowLimit, highLimit, qstate);
		} else {			
			questions = qRepository.findLimit(lowLimit, highLimit);
		}
		
		return questions;
	}
	
	
}
