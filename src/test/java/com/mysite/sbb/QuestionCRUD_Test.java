package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
public class QuestionCRUD_Test {

	// Question 테이블의 CRUD
	
	// Question 테이블을 CRUD하는 레파지토리를 스프링 프레임워크에서 객체 주입해줌 : @Autowired --> Test
	// QuestionRepository questionRepository = new QuestionRepository() ; --> 개발자가 객체를 생성
	
	@Autowired
	QuestionRepository questionRepository;
	
	// Insert test
	//@Test
	void jpaInsert () {
		
		Question q1 = new Question() ;
		
		q1.setSubject("백엔드가 무엇인가요?");
		q1.setContent("백엔드가 구체적으로 무엇인가요?");
		q1.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q1);
	}
	
	// Update test : 기존의 레코드를 가지고 오고 setter를 통해서 수정 후 저장
	
	//@Test
	void jpaUpdate () {
		
		Optional<Question> op = questionRepository.findById(102);
		 
		// 끄집어낼 때는 반드시 조건 처리
		
		
		Question q = null ; // 블락안에서 선언하면 블락안에서 밖에 사용을 못해서
		
		if (op.isPresent()) {  // op의  Question 객체를 끄집어 낼 때 존재할 때 ( null이 아닐 때)
			
			q = op.get();  // 102번을 gethering 한 것
			 
		}
		
		// setter를 사용해서 수정할 내용을 필드에 입력
		
		q.setSubject("수정된 제목입니다.");
		
		questionRepository.save(q);			// update
		
		
		}
	
	
	// Delete test : delete() , delete할 레코드를 가지고 와서 
	
	//@Test
	void jpaDelete () {
		
		Optional<Question> op = questionRepository.findById(102);
		 
		// 끄집어낼 때는 반드시 조건 처리
		
		
		Question q = null ; // 블락안에서 선언하면 블락안에서 밖에 사용을 못해서
		
		if (op.isPresent()) {  // op의  Question 객체를 끄집어 낼 때 존재할 때 ( null이 아닐 때)
			
			q = op.get();  // 102번을 gethering 한 것
			 
		}
		
		
		questionRepository.delete(q);		// delete
		
		
		}
	
	
	// Select test 
	
	//@Test
	void jpaSelect () {
		
		List<Question> list = questionRepository.findAll();
		
		// for문을 사용해서 list의 question 객체를 끄집어내서 출력
		
		for(int i = 0 ; i < list.size() ; i++) {
			System.out.println(list.get(i).getSubject());
		}
		
		assertEquals(2, list.size());
	}
	
	
	// 해당 질문에 대한 모든 답변을 가지고 오기  
	
	@Transactional
	@Test
	void jpaQuestionAnswerList () {
		
		Optional<Question> op = questionRepository.findById(1);
		
		Question q = null ; 
		
		if (op.isPresent()) {
			
			q = op.get() ;
		}
		
		// 1번 질문에 대한 모든 답변 리스트가 저장되어 있음
		List<Answer> answerlist = q.getAnswerList();
		
		for (int i = 0 ; i<answerlist.size() ; i++ ) {
			System.out.println(answerlist.get(i).getContent());
		}
		
		assertEquals(4, answerlist.size());
	}
}
