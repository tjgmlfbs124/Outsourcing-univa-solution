package com.tathink.univa.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.domain.Problem;
import com.tathink.univa.domain.Solution;
import com.tathink.univa.repository.SolutionRepository;

@SpringBootTest
@Transactional
public class SolutionServiceTest {
	
	@Autowired SolutionService sService;
	@Autowired SolutionRepository qRepository;
	
	@Test	
	void 질문등록() {
//		Solution question = new Solution();
//		question.setTitle("제목테스트");
//		question.setNickname("아무개");
//		question.setPassword("pwd");
//		
//		Solution mSolution = qRepository.save(question);
//		
//		//Solution findQuestion = sService.findOne(saveId).get();
//		assertThat(question.getTitle()).isEqualTo(mSolution.getTitle());
//		// System.out.println(findQuestion.getNickname()); // 아무개 출력
	}
	
	@Test
	void 모든질문() {
		List<Solution> questions = sService.findAllQuestions();
		//System.out.println(questions.size());
		
		for(int i=0; i<questions.size(); i++) {
			//System.out.println(questions.get(i).getNickname() );
		}
	}
	
	@Test
	void 최근10개() {
		int amount = 10;
		List<Solution> questions = sService.findRecently(amount);
		//System.out.println(questions.size());
		assertThat(questions.size()).isEqualTo(amount);
		
		for(int i=0; i<questions.size(); i++) {
			//System.out.println("title: "+questions.get(i).getTitle());
		}
	}
	
	@Test
	void 최근3번째부터5개까지() {
//		List<Solution> questions = sService.findList(3, 5, 0); // 3, 4, 5, 6, 7 (5 row)
//		//System.out.println(questions.size());
//		for(int i=0; i<questions.size(); i++) {
//			//System.out.println("title: "+questions.get(i).getTitle());
//		}
	}
	
	@Test
	void 질문의문제받아오기() {
		//int id = 40;
		Solution sol = new Solution();
		sol.setId(40);
		List<Problem> problems = sService.findProblem(sol);
		for(int i=0; i<problems.size(); i++) {
			//System.out.println("content :"+ problems.get(i).getText());
		}
	}
}
