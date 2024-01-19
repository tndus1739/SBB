package com.mysite.sbb;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	// 객체를 DI ( 주입 ) : 객체를 Spring Framework에 등록
	// 객체 생성 (java) : A a = new A();
	// 객체 생성 (spring) : @Autowired -> QuestionRepository클래스를 스프링 프레임워크를 통해 questionRepository에 객체를 주입시킴
	
	@Autowired // Test 코드에서 주로 사용
	private QuestionRepository questionRepository ; // 객체화가 됨
		// 클래스이름(첫자대문자)    //  변수이름
	
	@Test
	void jpaInsertTest() {
		
		Question q1 = new Question () ;
		
		q1.setSubject("JPA가 뭐야?");
		q1.setContent("JPA가 구체적으로 뭔지 알고 싶음");
		q1.setCreateDate(LocalDateTime.now());  // LocalDateTime.now() : 현재 글쓰고 있는 시간
		
		questionRepository.save(q1);    // db에 저장됨
		 
		Question q2 = new Question () ;
		
		q2.setSubject("스프링 부트가 뭐야?");
		q2.setContent("스프링 부트가 구체적으로 뭔지 알고 싶음");
		q2.setCreateDate(LocalDateTime.now());  // LocalDateTime.now() : 현재 글쓰고 있는 시간
		
		questionRepository.save(q2);
		
	}

}
