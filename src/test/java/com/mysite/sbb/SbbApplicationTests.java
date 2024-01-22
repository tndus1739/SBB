package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
	
	// Question  테이블의 CRUD
	

	// 객체를 DI ( 주입 ) : 객체를 Spring Framework에 등록
	// 객체 생성 (java) : A a = new A();
	// 객체 생성 (spring) : @Autowired -> QuestionRepository클래스를 스프링 프레임워크를 통해 questionRepository에 객체를 주입시킴
	
	@Autowired // Test 코드에서 주로 사용
	private QuestionRepository questionRepository ; // 객체화가 됨
		// 클래스이름(첫자대문자)    //  변수이름
	
	
	// Insert Test
     //@Test
	void jpaInsertTest() {
		
		Question q1 = new Question () ;
		
		q1.setSubject("JPA가 무엇인가요?");
		q1.setContent("JPA가 구체적으로 무엇인가요?");
		q1.setCreateDate(LocalDateTime.now());  // LocalDateTime.now() : 현재 글쓰고 있는 시간
		
		questionRepository.save(q1);    // db에 저장됨
		 
		Question q2 = new Question () ;
		
		q2.setSubject("스프링 부트가 무엇인가요?");
		q2.setContent("스프링 부트가 구체적으로 무엇인가요?");
		q2.setCreateDate(LocalDateTime.now());  // LocalDateTime.now() : 현재 글쓰고 있는 시간
		
		questionRepository.save(q2);
		
	}

	//@Test
	void jpaSelectListTest() {   // find해서 객체를 LIst에 담음
		
		
		// 모든 객체를 가져와서 List에 담음
		List<Question> q = this.questionRepository.findAll(); // List<Question> : Question 객체가 다 담겨 있음
		
		Question q1 = q.get(0);    // 첫번째 방에 있는 question 객체를 끄집어 냄
		Question q2 = q.get(1);
	
		System.out.println(q1.getSubject());
		System.out.println(q2.getSubject());
		
		// questionRepository은 question 테이블과 연결
		// findAll() : 테이블의 모든 레코드를 가져와서 question 객체에 담아서 List 에 담아줌
		
		// assertEquals (기대치[전체 레코드 개수] , 값 );  -> 기대치 = 값 ( 기대치와 값이 같을 때 정상 ) // 톰캣과 Junit은 멀티 커넥션이 안됨
		assertEquals(10, q.size()); 
		
		assertEquals("JPA가 무엇인가요?", q1.getSubject());
		
	}
		//@Test
		void jpaSelectTest () {
			
			// question 테이블의 레코드를 1개만 가져옴
			// Optional 은 null 처리를 쉽게 해줌
				// Optional은 값이 하나 밖에 안들어가지 때문에 방번호를 넣으면 오류
				// isPresent() : 값이 존재하면 true
				// isEmpty () : 값이 존재하지 않으면 true
			// select * from question where id = 1 ;
			
			// Optional의 Question 객체 ( Question객체를 Optional안에 담게 된다)
			Optional<Question> op = questionRepository.findById(1);
			
			if ( op.isPresent())   {     // 값이 Null인데 값을 끄집어내면 오류가 나기 때문에 NUll이 아닐 때 Optional 내부의 Question 객체를 끄집어 냄
											// 값이 존재할 때 끄집어 내야 한다.
				Question q = op.get();
				
				System.out.println("글내용출력 :"  + q.getContent());
				System.out.println("글제목출력 :"  + q.getSubject());
			
				assertEquals("JPA가 무엇인가요?", q.getSubject());
			
			}
			
		}
			
		//@Test  
		void jpaSelectSubject () {   
				
			List<Question> list =questionRepository.findBySubject("JPA가 무엇인가요?");
			
			if (list != null) {
				
				Question q = list.get(0);
				
				System.out.println(q.getSubject());
			
				assertEquals("JPA가 무엇인가요?", q.getSubject());
				
			}
			
		}
		
		//@Test	
		void jpaSelectContent () {
			
			List<Question> list = questionRepository.findByContent("스프링 부트가 구체적으로 무엇인가요?");
			
			if ( list != null ) {
				Question q = list.get(0);
				
				System.out.println(q.getSubject());
			
				assertEquals("스프링 부트가 구체적으로 무엇인가요?", q.getContent());
				
				
				
			}
			
			
		}
			
			
		
		//@Test	
		void jpaSelectSubjectLike () {
					
		//select * from question where subject like '%?%';
		List<Question> list = questionRepository.findBySubjectLike("%JPA%");
					
		if ( list != null ) {
			Question q = list.get(0);
						
			System.out.println(q.getSubject());
			System.out.println(q.getContent());		
			
			assertEquals("JPA가 무엇인가요?", q.getSubject());
						
		}
					
	}
		//@Test			
		void jpaSelectSubjectAndContent() {
			
		// select * from question where subject = "?" and content = "?" ;	
		List<Question> list = questionRepository.findBySubjectAndContent("JPA가 무엇인가요?", "JPA가 구체적으로 무엇인가요?");	
			
		if ( list != null){
			
			Question q = list.get(0);
			System.out.println(q.getSubject());
			System.out.println(q.getContent());		
			
			assertEquals("JPA가 무엇인가요?", q.getSubject()); 
					//   기대치           , 값
			assertEquals("JPA가 구체적으로 무엇인가요?", q.getContent());
			
			}
		
		}
		
		// 데이터 수정하기 : save() : insert , update ( 언제 insert가 되고 언제 update가 되는지 )
		
		//@Test
		void jpaUPdate () {
			
		// 1. update : 기존의 레코드를 가지고 와서 setter 로 수정 후 save ()
		   
		Optional<Question> op = questionRepository.findById(1);   // 레코드가 1개이기 때문에 List가 아니라 Optional
		
		if (op.isPresent()) {
			
			Question q = op.get();
			
			// question 테이블의 id = 1 인 값을 가지고옴 < 숭
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
			
			q.setSubject("수정");
			
			questionRepository.save(q);     // update
			
			assertEquals("수정", q.getSubject());
			
			}
			
		}
		
		// 데이터 입력 : save() : insert , update ( 언제 insert가 되고 언제 update가 되는지 )
		
		//@Test
		void jpaInsert2 () {
		
		// 2. insert :객체를 만들어서 setter로 값을 다 주입
			
			Question q = new Question () ;
			
			q.setSubject("오늘의 날씨");
			q.setContent("오늘 날씨의 상세정보");
			q.setCreateDate(LocalDateTime.now());

			questionRepository.save(q);  // insert
		}
		
		// 데이터 삭제 : delete ()
		
		@Test
		void jpaDelete () {
			// delete 할 레코드(Question)를 가져와서 , delete(Question)
			// ID = 52
			
			Optional <Question> op =questionRepository.findById(52);
			
			if (op.isPresent()) {
				
				Question q = op.get();
				
				questionRepository.delete(q);  // id=52 레코드 삭제
				
				
			}
			
		}
		
		
}
