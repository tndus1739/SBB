package com.mysite.sbb.answer;

import java.security.Principal;

import org.hibernate.query.sqm.tree.select.SqmQuerySpec;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
	@PreAuthorize("isAuthenticated()")
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
	
	
	
	
}
