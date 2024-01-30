package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

@SpringBootTest
public class QuestionModify_Test {

	@Autowired
	private QuestionService questionService;
	
	//@Test
	void questionModefy_Test() {
		
		// 1. 수정할 Question 객체를 주입
		// 2. 수정할 제목
		// 3. 수정한 내용
		
		Question q = 
				questionService.getQuestion(1552);
		
		questionService.modify(q, "수정수정수정" , "수정내용내뇽ㄴ");
							// queston객체 , 수정된제목 , 수정된 내용
	}
	
	
}
