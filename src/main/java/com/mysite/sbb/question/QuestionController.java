package com.mysite.sbb.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbb.answer.AnswerForm;

import jakarta.validation.Valid;
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
	public String list(Model model,
			@RequestParam(value = "page" , defaultValue="0") int page
			) {
		
		// Model : 서버의 데이터를 client view 페이지로 전송
		// 메소드 인풋값으로 선언되면 객체가 자동으로 생성됨
		
		// client 요청에 대한 비즈니스 로직 처리 : question 테이블의 list를 출력
		//List<Question> questionList = questionService.getList();
		
		// 페이징 처리된 객체를 받음
		Page<Question> paging = questionService.getList(page);
		
		// paging에 등록되어 있는 중요 메소드 출력
		System.out.println("전체 레코드 수 :" + paging.getTotalElements());
		System.out.println("페이지당 출력 레코드 개수 : " + paging.getSize());
		System.out.println("전체 페이지 개수 :" + paging.getTotalPages());
		
		System.out.println("현재 요청 페이지 번호 :" + paging.getNumber());
		System.out.println("이전 페이지 존재 여부 :" + paging.hasPrevious());
		System.out.println("다음 페이지 존재여부 : " + paging.hasNext());
		
		
		// model에 담아서 client view 페이지로 전송
		//model.addAttribute("questionList",questionList);
		                  //client에게서 가져올 변수 //객체:list안에 question 객체가 담겨있음
		
		model.addAttribute("paging",paging);
		
		//templates/question_list.html
		// thymeleaf 라이브러리  설치시 view page가 위치할 곳 ( .html)
		return "question_list";
	}

	// 상세 글 조회
	@GetMapping("/detail/{id}")
	public String detail (Model model , 
			@PathVariable("id") Integer id,
			AnswerForm answerForm
			) {
		
		System.out.println(id);
		
		// 백엔드의 로직 처리
		Question question = questionService.getQuestion(id);
		
		System.out.println(question.getSubject());
		System.out.println(question.getContent());
		
		// model에 담아서 client로 전송
		model.addAttribute("question" , question);
		return "question_detail" ;
	}

	// 질문 등록 하기 : 글 등록 뷰페이지만 전송
	//http://localhost:8585/question/create
	@GetMapping("/create")  // @GetMapping : 뷰페이지 던져줌
	public String questionCreate(QuestionForm questionForm) {   // questionForm 객체가 들어와야함
		
		return "question_form";
	}
	
	// 질문등록 DB에 값을 받아서 저장
	@PostMapping("/create")
	public String questionCreate (
			
//			@RequestParam("subject") String subject, 
//			@RequestParam("content") String content
			@Valid QuestionForm questionForm, BindingResult bindingResult //@Valid : 들어온 값을 체크
			) {
		
		if (bindingResult.hasFieldErrors()) {
			
			return "question_form";
			
		}
		
	
//		System.out.println("제목 : " + subject);
//		System.out.println("내용 : " + content);
		
		questionService.create(questionForm.getSubject(), questionForm.getContent());
		
		return "redirect:/question/list";
	}
	
}
