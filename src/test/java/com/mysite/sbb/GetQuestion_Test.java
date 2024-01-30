package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

@SpringBootTest
public class GetQuestion_Test {
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	public void GetQuestion_Test () {
		
		Question q =questionService.getQuestion(1452);
		
		System.out.println(q.getSubject());
		System.out.println(q.getContent());
		
	}

}
