package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service     // 빈등록 : 클래스를 객체화해서 spring framework에 등록
public class QuestionService {

	// controller --> service --> repository
	
	public static Question get;
	private final QuestionRepository questionRepository;
	
	// question 테이블의 모든 레코드를 가져와서 리턴
	// 리스트 페이지
	// 페이징 처리되지 않는 모든 레코드를 출력
	public List<Question> getList () {
		
		return questionRepository.findAll();	// findAll한 값이 return 됨
		
	}  
	
	
	// 상세 페이지 ( question id 값을 인풋 받아서 question 객체를 찾아서 리턴 )
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> op = questionRepository.findById(id);
		
		return op.get();
	}
	
	// question 테이블에 값 insert
	public void create(String subject, String content) {
		
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		
		questionRepository.save(q);
	}
	
	// client에서 요청할 페이지 번호를 매개변수로 입력 : 
	public Page<Question> getList(int page) {
	  
	//page : 요청하는 페이지 번호 , 10 : 한페이지에 출력하는 레코드 개수
	  Pageable pageable = PageRequest.of(page, 20);
	  
	  return questionRepository.findAll(pageable);
	  
  }
	
	
}
