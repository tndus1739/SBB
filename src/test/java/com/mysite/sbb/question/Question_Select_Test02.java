package com.mysite.sbb.question;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;
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
		
		System.out.println("============== 향상된 for문 ===========");
		
		for (Question q : list) {
			System.out.println(q.getId());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
		}
		
		System.out.println("==========Itertator 출력 : 순회자 ( 방번호가 없음) : set, map , page ===============");
		
		// Iterator 선언 
		Iterator<Question> iterator = list.iterator();  
		
		// while 문으로 출력 : iterator.hasNext() --> 다음의 값이 존재하면 true
		//                   iterator.next() --> 현재 위치의 객체를 끄집어 내고 다음 위치로 이동 // 과일바구니 안에 들어있는 객체를 순회하면서 끄집어 냄

		while (iterator.hasNext()) {
			
			Question q = iterator.next();
			
			System.out.println(q.getId());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
				
			
		}
		
		
	}
	
	
	
	
}
