package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Question_Update_Test {

	@Autowired
	private QuestionRepository 	questionRepository ;
	
	@Test
	void question_Update_Test () {
		
		Optional<Question> op =
		questionRepository.findById(2002); // long 타입일때만  L을 붙여준다.
		
		Question question = op.get();
		
		question.setSubject("수정제목-------");
		question.setContent("수정내용---------");
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question);
		
	}
	
	
}
