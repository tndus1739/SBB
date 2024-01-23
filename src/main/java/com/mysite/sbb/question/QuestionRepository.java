package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {   //★★★★★ 중요함
	
	// DAO 라고 보면 됨
	
	// 인터페이스 이름 짓는 방법 : Entity 클래스의 이름 + Repository
	
	// 상위 인터페이스의 모든 메소드가 상속 되어서 내려옴
	
/*	JpaRepository 에서 선언된 메소드가 상속되어 내려옴
	
	findAll()   : select * from question ( 각 각의 question 객체를 List에 담음 , 여러개의 Question 객체를 담음 [레코드 여러개])
		List<Question> q = questionRespository.findAll();
		
	findById(1) : select * from question where id = 1 ;  (1개의 Question 객체를 담음 [레코드 1개])
		Optional<Question> op = questionRepository.findById(1);
		
	save()      : insert, update
	delete()    : delete

*/
//==============================================================================================================
	
	// 제목으로 검색 - 메소드를 생성해야 함 : 테이블의  Primary Key 컬럼 외의 컬럼은 조건으로 검색할 경우 메소드를 만들어야 함
	
	// select * from question where subject = "?" ;
	//	Optional<Question> findBySubject(String subject);  // findBySubject : 메소드
	// optional은 하나의 값만  담을 수 있음 (여러개의 값을 담으면 오류 , 여러개의 값을 담고 싶으면 List 사용)
	
	// 내용으로 검색
	
	// select * from question where content = "?" ;
	//	Optional<Question> findByContent(String content);  // findByContent : 메소드
		
		
//=================================================================================================================countBy// 제목으로 검색 - 메소드를 생성해야 함 : 테이블의  Primary Key 컬럼 외의 컬럼은 조건으로 검색할 경우 메소드를 만들어야 함
	
	
	// 제목으로 검색 - 메소드를 생성해야 함 : 테이블의  Primary Key 컬럼 외의 컬럼은 조건으로 검색할 경우 메소드를 만들어야 함
	// select * from question where subject = "?" ;
		
		List<Question> findBySubject(String subject);  // findBySubject : 메소드
		
	// optional은 하나의 값만  담을 수 있음 (여러개의 값을 담으면 오류 , 여러개의 값을 담고 싶으면 List 사용)
		
	// 내용으로 검색 - - 메소드를 생성해야 함
		
	// select * from question where content = "?" ;
		List<Question> findByContent(String content);  // findByContent : 메소드		
		
	//==========================================================================================================
		
		// 대소문자 구별
		
		// 제목으로 검색 : like
		
		// select * from question where subject like '%?%' ;
		List<Question> findBySubjectLike(String subject);
			//List<Question> list = questionRepository.findByContent("%JPA%");
		
		// 내용으로 검색 : like
		
		// select * from question where content like '%?%' ;
		List<Question> findByContentLike(String content); 

		
		// 제목과 내용으로 검색 ( select한 값을 return 값으로 받음)
		// select * from question where subject = "?" and content = "?" ;
		List<Question> findBySubjectAndContent(String subject , String content); 

		
		
		
		
		
		
		
		
		
}
