package com.mysite.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.websocket.server.PathParam;


@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final AnswerService answerService;
	
	// 답변 등록 처리
	@PostMapping("/answer/create/{id}")
	public String createAnswer (
			Model model , 
			@PathVariable ("id") Integer id ,
			@RequestParam(value="content") String content
			) {
		
		System.out.println("question id : " + id);
		System.err.println("content : " + content);
		
		answerService.creatAnswer(id, content);
		
		return String.format("redirect:/question/detail/%s", id);  // id값이 %s에 주입
	}
	
	
	
	
}
