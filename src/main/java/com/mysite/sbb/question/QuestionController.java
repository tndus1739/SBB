package com.mysite.sbb.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")  // 하의 @GetMapping , @PostMapping의 prefix가 적용됨
@RequiredArgsConstructor // 보안이나 여러 문제들을 해결
@Controller
public class QuestionController {
	
	// @Autowired : 타입으로 객체를 주입하기 때문에 동일한 타입이 주입될 수 있다. so, Junit test에서 사용
	
	// 생성자를 통한 객체 주입
	//private final QuestionRepository questionRepository;
	
	private final QuestionService questionService;

	
	// 글목록 조회
	
	//http://localhost:8585/question/list
	@GetMapping("/list") // "/question/list"로 요청이 들어오면
	//@ResponseBody  
	// @ResponseBody :문자열 자체를 return으로 돌려줌 /  @ResponseBody가 생략이 되면 뷰페이지를 보여줌
	public String list(Model model) {
		
		// Model : 서버의 데이터를 client view 페이지로 전송
		// 메소드 인풋값으로 선언되면 객체가 자동으로 생성됨
		
		// client 요청에 대한 비즈니스 로직 처리 : question 테이블의 list를 출력
		
		List<Question> questionList = questionService.getList();
		
		// model에 담사서 client view 페이지로 전송
		model.addAttribute("questionList",questionList);
		                //client에게서 가져올 변수 //객체:list안에 question 객체가 담겨있음
		
		//templates/question_list.html
		// thymeleaf 라이브러리  설치시 view page가 위치할 곳 ( .html)
		return "question_list";
	}

	// 상세 글 조회
	@GetMapping("/detail/{id}")
	public String detail (Model model , @PathVariable("id") Integer id ) {
		
		System.out.println(id);
		
		// 백엔드의 로직 처리
		Question question = questionService.getQuestion(id);
		
		System.out.println(question.getSubject());
		System.out.println(question.getContent());
		
		// model에 담아서 client로 전송
		model.addAttribute("question" , question);
		return "question_detail" ;
	}


}
