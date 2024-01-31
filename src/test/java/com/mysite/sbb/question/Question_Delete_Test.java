package com.mysite.sbb.question;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Question_Delete_Test {
	
	@Autowired
	private  QuestionRepository questionRepository;
	
	@Test
	void Question_Delete_Test () {
		
		Optional<Question> op =
		questionRepository.findById(2052);
         
		// 1번째 방법
		questionRepository.delete(op.get());
	
		// 2번째 방법
		Question q= op.get();
		questionRepository.delete(q);

	
	}
	
	
	
}
