package com.tathink.univa.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tathink.univa.domain.Question;
import com.tathink.univa.repository.QuestionRepository;

@SpringBootTest
@Transactional
public class QuestionServiceTest {
	
	@Autowired QuestionService qService;
	@Autowired QuestionRepository qRepository;
	
	@Test
	void 질문등록() {
		Question question = new Question();
		question.setTitle("제목테스트");
		question.setNickname("아무개");
		question.setPassword("pwd");
		
		int saveId = qService.apply(question);
		
		Question findQuestion = qService.findOne(saveId).get();
		assertThat(question.getTitle()).isEqualTo(findQuestion.getTitle());
		// System.out.println(findQuestion.getNickname()); // 아무개 출력
	}
	
	@Test
	void 모든질문() {
		List<Question> questions = qService.findAllQuestions();
		//System.out.println(questions.size());
		
		for(int i=0; i<questions.size(); i++) {
			//System.out.println(questions.get(i).getNickname() );
		}
	}
	
	@Test
	void 최근10개() {
		int amount = 10;
		List<Question> questions = qService.findRecently(amount);
		//System.out.println(questions.size());
		assertThat(questions.size()).isEqualTo(amount);
		
		for(int i=0; i<questions.size(); i++) {
			//System.out.println("title: "+questions.get(i).getTitle());
		}
	}
	
	@Test
	void 최근3번째부터5개까지() {
		List<Question> questions = qService.findList(3, 5, 0); // 3, 4, 5, 6, 7 (5 row)
		//System.out.println(questions.size());
		for(int i=0; i<questions.size(); i++) {
			//System.out.println("title: "+questions.get(i).getTitle());
		}
	}
}
