package com.mysite.sbb;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.answer.AnswerService;

@SpringBootTest
public class AnswerModify_Test {
	
	@Autowired   // @Autowired  : 필드마다 각 각 다 넣어줘야 오류가 발생하지 않는다.
	private AnswerService answerService;
	
	
	@Autowired // 필드마다 각 각 다 넣어줘야 오류가 발생하지 않는다.
	private AnswerRepository answerRepository;	
	
	@Test
	void AnswerModify_Test() {
		
		Optional<Answer> op = answerRepository.findById(1004);
		
		Answer answer = op.get();
		
		answerService.modify(answer, "수정--------------");
	}
	
	
	
	
}
