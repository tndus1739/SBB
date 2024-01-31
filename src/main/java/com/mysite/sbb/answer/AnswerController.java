package com.mysite.sbb.answer;

import java.security.Principal;

import org.hibernate.query.sqm.tree.select.SqmQuerySpec;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;


@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final AnswerService answerService;
	private final QuestionService questionService;
	private final UserService userService;
	
	// 답변 등록 처리
	@PreAuthorize("isAuthenticated()")  // 인증된 상태에서만 가져오기
	@PostMapping("/answer/create/{id}")
	public String createAnswer (
			Model model , 
			@PathVariable ("id") Integer id ,
//			@RequestParam(value="content") String content
			@Valid  AnswerForm answerForm , BindingResult bindingResult,
			Principal principal
			) {
		
		//뷰에서 인증된 사용자 정보를 가지고 오는 객체
		// 인증된 계정 정보가 출력 
	    //System.out.println("뷰에서 인증된 계정 정보를 출력 : " + principal.getName());
		
		Question question = questionService.getQuestion(id);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
			
			// 메세지 출력안하고 새롭게 리다이렉트로 이동됨
			// 오류 : return String.format("redirect:/question/detail/%s", id);
		}
		
		System.out.println("question id : " + id);
		System.out.println("content : " + answerForm.getContent());
		
		// principal.getName() : 현재로그인 한 사용자 정보가 넘어옴. 
		// 수정 추가됨 
		SiteUser siteUser = userService.getUser( principal.getName() ) ; 
				
		// 수정됨 
		
		
		answerService.creatAnswer(id, answerForm.getContent(), siteUser);
		
		return String.format("redirect:/question/detail/%s", id);  // id값이 %s에 주입
	}
		
		/*
		 @@GetMapping : 뷰페이지를 던져줌
		 @PostMapping : 링크로 보내줌
		 
		  */
	
		//  답변을 수정할 수 있는 뷰 페이지 전송 
	
		@PreAuthorize("isAuthenticated()")
		@GetMapping("/answer/modify/{id}")
		
		public String answerModify(
				AnswerForm answerForm,  // 빈껍데기
				@PathVariable("id") Integer id,
				Principal principal
				
				) {
			
			// id값에 해당하는 answer 객체를 찾아온다. 
			Answer answer = answerService.getAnswer(id);
			
			// 현재 로그온한 사용자가 자신이 작성한 답변이 아닌경우 예외 (오류를 강제로 발생)
			// 자신이 작성한 글이 아닐 경유 :  강제 오류 발생
			
			// 현재 로그온한 계정 
			if (! principal.getName().equals(answer.getAuthor().getUsername())) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "당신은 수정권한이 없습니다.");
			}
		
			answerForm.setContent(answer.getContent());
			
			
			return "answer_form";
		}
	
	
		// 수정 폼에서 넘어오는 값을 받아서 저장
		
		
		@PreAuthorize("isAuthenticated()")
		@PostMapping("/answer/modify/{id}")
		public String answerModify (
				 @PathVariable("id") Integer id,         // String이 자동으로 Integer 로 변환되어서 들어옴
				 AnswerForm answerForm,
				 Principal principal
				
				){
			
			Answer answer = answerService.getAnswer(id);
			
			// 수정완료
			answerService.modify(answer, answerForm.getContent());
			
			// 수정 완료 후 이동 페이지 
			return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
		}
	
	
}
