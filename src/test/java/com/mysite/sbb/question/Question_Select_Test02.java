package com.mysite.sbb.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Question_Select_Test02 {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void question_Select_Test02 () {
		
		// 모든 레코드 출력
		
		List<Question> list = questionRepository.findAll();
		
		// for문을 사용해서 List의 담신 question 객체를 끄집어 내서 출력
		
		for (int i = 0 ; i <list.size() ; i++) {
			
			System.out.println(list.get(i).getId());  // i 방번호의 id를 출력
			System.out.println(list.get(i).getSubject()); 
			System.out.println(list.get(i).getContent()); 
		}
		
		
		
		
	}
	
	
	
	
}
