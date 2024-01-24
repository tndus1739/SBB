package com.mysite.sbb.answer;

import org.hibernate.query.sqm.tree.select.SqmQuerySpec;
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

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;


@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final AnswerService answerService;
	private final QuestionService questionService;
	
	// 답변 등록 처리
	@PostMapping("/answer/create/{id}")
	public String createAnswer (
			Model model , 
			@PathVariable ("id") Integer id ,
//			@RequestParam(value="content") String content
			@Valid  AnswerForm answerForm , BindingResult bindingResult
			) {
		
		Question question = questionService.getQuestion(id);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
			
			// 메세지 출력안하고 새롭게 리다이렉트로 이동됨
			// 오류 : return String.format("redirect:/question/detail/%s", id);
		}
		
		System.out.println("question id : " + id);
		System.out.println("content : " + answerForm.getContent());
		
		answerService.creatAnswer(id, answerForm.getContent());
		
		return String.format("redirect:/question/detail/%s", id);  // id값이 %s에 주입
	}
	
	
	
	
}
