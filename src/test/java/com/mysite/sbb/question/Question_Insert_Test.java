package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserRepository;

@SpringBootTest
public class Question_Insert_Test {
		
	// 
	@Autowired // 필드마다 작성
	private QuestionRepository questionRepository;
	@Autowired // 필드마다 작성
    private UserRepository userRepository;
	
	@Test
	void question_Insert_Test () {
		
		Question q = new Question();   // entity 클래스 
		
		q.setSubject("JUnit 테스트에서 제목1111111111");
		q.setContent("Jnit 테스트에서 내용11111111111");
		q.setCreateDate(LocalDateTime.now());
		
		// siteuser 객체를 가지고 와야함
		
		// findById로 검색
		// Optional<SiteUser> op = userRepository.findById(1L); //// long타입이라서 1앞에 L 입력
		
		
		//findByUsername로 검색
		Optional<SiteUser> op = userRepository.findByUsername("user1");
		
		
		
		// optional 안의 siteuser 객체는 1개가 담겨있다. .
	
		// 담은 객체를 끄집어내 주어야 한다. 
		
		SiteUser user = op.get();
		
		q.setAuthor(user);
		
		questionRepository.save(q);
	}
	
	
}
