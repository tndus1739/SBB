package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  // answerRepository 객체 주입을 자동으로 해줌
@Service
public class AnswerService {
	
	// 생성자를 통한 객체 주입 : @RequiredArgsConstructor
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	
	// Insert , update , delete는 리턴타입이 없다. : void 선언
	// select는 리턴타입이 있다. : List<Answer> , Answer등을 리턴으로 돌려준다.
	
	public void creatAnswer ( Integer id , String content) {
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		
		Optional<Question> op = questionRepository.findById(id);
		
		Question question = null;
		
		if(op.isPresent()) {
			
			question = op.get();
		
		}
		
		answer.setQuestion(question);
		answerRepository.save(answer);
	
	}
	
}
