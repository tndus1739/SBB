package com.mysite.sbb;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerCRUD_Test {
	
	// Question : 부모 테이블
	// Answer : 자식 테이블

	@Autowired
	 QuestionRepository questionRepository;
	
	@Autowired
	 AnswerRepository answerRepository;
	
	//@Test
	void jpaInsert () {
		
		Optional<Question> op =  questionRepository.findById(152);
		Question q = null;
		
		if (op.isPresent()) {
			
			q = op.get();
		}
		//
		Answer a1 = new Answer () ;
		 
		a1.setContent("보이지 않는 비즈니스 로직을 처리하는 힘");
		a1.setCreateDate(LocalDateTime.now());
		a1.setQuestion(q);  // 주의
		
		answerRepository.save(a1);
		//
		Answer a2 = new Answer () ;
		 
		a2.setContent("쉬운 유지보수");
		a2.setCreateDate(LocalDateTime.now());
		a2.setQuestion(q);  // 주의
		
		answerRepository.save(a2);
		//
		Answer a3 = new Answer () ;
		 
		a3.setContent("코드 간결");
		a3.setCreateDate(LocalDateTime.now());
		a3.setQuestion(q);  // 주의
		
		answerRepository.save(a3);
	
	}
	
}
