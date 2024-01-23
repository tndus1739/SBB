package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service     // 빈등록 : 클래스를 객체화해서 spring framework에 등록
public class QuestionService {

	// controller --> service --> repository
	
	private final QuestionRepository questionRepository;
	
	// question 테이블의 모든 레코드를 가져와서 리턴
	// 리스트 페이지
	public List<Question> getList () {
		
		return questionRepository.findAll();	// findAll한 값이 return 됨
		
	}  
	
	
	// 상세 페이지
	public Question getQuestion(Integer id) {
		
		Optional<Question> op = questionRepository.findById(id);
		
		return op.get();
	}
	
	
	
}
