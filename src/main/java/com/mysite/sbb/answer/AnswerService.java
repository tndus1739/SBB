package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  // answerRepository 객체 주입을 자동으로 해줌
@Service
public class AnswerService {
	
	// 생성자를 통한 객체 주입 : @RequiredArgsConstructor
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	
	// Insert , update , delete는 리턴타입이 없다. : void 선언
	// select는 리턴타입이 있다. : List<Answer> , Answer등을 리턴으로 돌려준다.
	
	// 질문 등록 : question_id, content, author  
	
	public Answer creatAnswer ( Integer id , String content , SiteUser author) {  // Answer 객체를 return으로 돌려줌
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		
		Optional<Question> op = questionRepository.findById(id);
		
		Question question = null;
		
		if(op.isPresent()) {
			
			question = op.get();
		
		}
		
		answer.setQuestion(question);
		
		// 추가된 항목 : 사용자 정보 컬럼을 추가함 
				answer.setAuthor(author);
		
		
		answerRepository.save(answer);
	
		return answer;   // Answer 객체를 return으로 돌려줌
	}
	
	
		// 답변을 수정하는 메소드 : Answer <-- 수정할 Answer 객체가 주입되어야 함  <-- Controller
		
		public void modify(Answer answer, String content) {    // 항상 update는 기존의 정보를 끄집어내서 setter로 수정한 뒤에 다시 정보를 보내줘야한다. 
			answer.setContent(content);
			answer.setModifyDate(LocalDateTime.now());
			
			// update
			answerRepository.save(answer);
			
			
		}
	
		// answer의 ID를 input 받아서 answer 객체를 리턴
		public Answer getAnswer (Integer id) {
			Optional<Answer> op =
					answerRepository.findById(id);
			
			
			// op가 null이 아닐때  끄집어 낸다. (null일때 끄집어내면 오류발생)
			if (op.isPresent()) {       // null 아닐 때
				return op.get();
				
			}else {						// null 일 때
				// 예외를 강제로 발생시킴
				throw new DataNotFoundException("해당 내용은 Answer에 존재하지 않습니다");
			}
			
		}

		// 메소드를 만들어 놓음
		
		// service를 두는 이유는 모듈화시켜서 controller의 코드를 깔끔하게 해줌 
		// (service 없으면 반복코드 계속 사용) 
	
		
		// 삭제 
		public void delete (Answer answer) {
			// 삭제
			answerRepository.delete(answer);
		}
		
		
		// 추천을 DB에 저장하는 로직
		
		public void vote(Answer answer , SiteUser siteUser) {
			
			//answer
			answer.getVoter().add(siteUser);
			
			answerRepository.save(answer);
		}
	
}
