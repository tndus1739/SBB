package com.mysite.sbb.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Question_Select_Test01 {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void question_Select_Test01 () {
		
		// select : 검색 (findById (10)
		// 레코드 1개만 검색 : findById(?)
		
		Optional<Question> op =
				questionRepository.findById(1652);
		Question q = op.get();
		
		System.out.println(q.getId());
		System.out.println(q.getSubject());
		System.out.println(q.getContent());
		
		
		// 기대치 , 값  --> 일치할 때 성공
		assertEquals(1652, q.getId());
		
	}
	
	
	
	
}
