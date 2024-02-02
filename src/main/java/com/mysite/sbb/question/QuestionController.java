package com.mysite.sbb.question;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/question")  // 하의 @GetMapping , @PostMapping의 prefix가 적용됨
@RequiredArgsConstructor // 보안이나 여러 문제들을 해결
@Controller
public class QuestionController {
	
	// @Autowired : 타입으로 객체를 주입하기 때문에 동일한 타입이 주입될 수 있다. so, Junit test에서 사용
	
	// 생성자를 통한 객체 주입
	//private final QuestionRepository questionRepository;
	
	private final QuestionService questionService;
	private final UserService userService; 
	
	// 글목록 조회
	
	//http://localhost:8585/question/list?page=0&kw='스프링'
	@GetMapping("/list") // "/question/list"로 요청이 들어오면
	//@ResponseBody  
	// @ResponseBody :문자열 자체를 return으로 돌려줌 /  @ResponseBody가 생략이 되면 뷰페이지를 보여줌
	public String list(Model model,
			@RequestParam(value = "page" , defaultValue="0") Integer page,
			@RequestParam (value ="kw", defaultValue="") String kw    // 검색어가 공백이면 모든 레코드값이 출력
			) {									// null 이 아니고 빈값을 넣겠다라는 뜻
		
		
		// 로그에서 출력 : 서버에 배포된 상태에서 변수 값을 출력
		log.info("page:{} , kw:{}" , page , kw);
		
		// 콘솔에서 출력 : 개발시에 변수값이 잘 들어오는지 출력
		System.out.println("page :" + page);
		System.out.println("kw :" + kw);
		
		// Model : 서버의 데이터를 client view 페이지로 전송
		// 메소드 인풋값으로 선언되면 객체가 자동으로 생성됨
		
		// client 요청에 대한 비즈니스 로직 처리 : question 테이블의 list를 출력
		//List<Question> questionList = questionService.getList();
		
		// 페이징 처리된 객체를 받음
		Page<Question> paging = questionService.getList(page , kw);
		
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
		model.addAttribute("kw",kw);
		
		//templates/question_list.html
		// thymeleaf 라이브러리  설치시 view page가 위치할 곳 ( .html)
		return "question_list";
	}

	// 상세 글 조회
	// http://localhost:8585/question/detail/{id}
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
	// 인증된 사용자만 접근 가능 				
			//<=== Spring Security 설정 :SecurityConfig.java , @EnableMethodSecurity(prePostEnabled=true)
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate (
			
//			@RequestParam("subject") String subject, 
//			@RequestParam("content") String content
			@Valid QuestionForm questionForm, BindingResult bindingResult,  //@Valid : 들어온 값을 체크
			Principal principal
			) {
		
		System.out.println("###현재 로그온한 계정 : " + principal.getName());
		
		if (bindingResult.hasFieldErrors()) {
			
			return "question_form";
			
		}
		
	
//		System.out.println("제목 : " + subject);
//		System.out.println("내용 : " + content);
		
		
		SiteUser siteUser = userService.getUser(principal.getName()); 
		
		questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		
		return "redirect:/question/list";
	}
	
	// 질문 정보를 가져와서 뷰페이지로 값을 넣어줌
	// http://localhost:8585/question/modify/1
	@GetMapping("/modify/{id}")
	public String questionModify(
			
			QuestionForm questionForm,
			@PathVariable("id") Integer id,
			Principal principal
			
			) {
		
			// Principal.getName()  : 현재 로그인한 사용자 정보를 출력
			// 넘어오는 id변수를 가져와서 수정할 question 객체를 끄집어 옴
		
			// save() - insert
			// save() - update --> 기존의 DB의 레코드(Question) 를 끄짐어내서 수정
		
	 Question q = questionService.getQuestion(id);
	 
	 // System.out.println("컨트롤러에서 내용 출력" + q.getSubject());
	 // System.out.println("컨트롤러에서 제목 출력" + q.getContent());
		
	// q에 저장된 subject, content필드의 값을 questionForm에 넣어서 클라이언트로 전송
	 questionForm.setSubject(q.getSubject());   // getter로 꺼내온 값을 setter로 다시 값을 넣어서 questform 에 주입
	 questionForm.setContent(q.getContent());	
		 
		return "question_form" ;
	}
	
	// 질문 수정된 내용을 받아서 DB에 저장 , save() 기존의 question 객체를 끄집어내서 수정 후 저장
	
	// http://localhost:8585/question/modify/1 
	@PostMapping("/modify/{id}")  // 폼에서 넘어옴
	public String quetionModify (
			@Valid QuestionForm questionForm,
			BindingResult bindingResult,
			@PathVariable("id") Integer id,
			Principal principal
			
			) {
		
		// questionForm 에 주입된 값을 확인
		if (bindingResult.hasErrors()) {
			return "question_form" ;
		}
		
		// 수정된 값을 DB에 저장하는 Service 메소드 호츨
			// 수정할  Question 객체를 끄집어냄
		
		Question q = questionService.getQuestion(id);
		questionService.modify(q, questionForm.getSubject(), questionForm.getContent());
		
		// 수정 이후에 이동할 페이지
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
	// 삭제 요청에 대한 처리
	// 원래는 자기계정으로 작성한 글만 삭제가 되어야하는데 링크로 delete 입력해서 들어가면 다른 계정으로 작성한 글도 삭제가 되어버리기 때문에 메소드 수정이 필요
	
	@GetMapping("/delete/{id}")
	public String questionDelete (
			@PathVariable ("id") Integer id,
			Principal principal
			) {
	// id 값을 가지고 Question 객체 
	Question q = questionService.getQuestion(id);
	
	
	// URL을 사용해서 삭제할 수 없도록 한다.
	// 현재 로그온한 계정과 DB의 저장된 username과 같을 때만 delete ( 같지 않을 때 예외 발생)
	if (! principal.getName().equals(q.getAuthor().getUsername())) {
		
		// 예외를 강제로 발생시킴 -- questionService.questionDelete(q); [이 블럭으로 못 넘어가게 하려고 ]
		//( 지금 넘어오는 유저정보와 DB안에 저장되어 있는 유저정보가 같아야 함)
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제할 권한이 없습니다. ");
	}
	
	
	// 삭제됨
	
	questionService.questionDelete(q);
	
		return "redirect:/";	//   "/" : 루트페이지
	
	}
	
	// 추천기능 추가
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")   // @GetMapping 할때는 $ 쓰면 안됨
	public String questionVoter(
			// client 에서 넘어오는 값을 처리
			@PathVariable("id") Integer id,
			Principal principal
			) {
		
		// 서비스에 로직을 처리
		// id는 question 테이블의 id컬럼의 값으로 question 객체를 끄집어냄
		
		Question question = questionService.getQuestion(id);
		//question voter에 값을 주입 : Set<SiteUser>
		//principal.getName()으로 SiteUser객체를 가지고 와야 함
		SiteUser siteUser = userService.getUser(principal.getName());
		
		// 투표완료됨
		questionService.vote(question, siteUser);
		
		// 뷰페이지로 전송
		
		return String.format("redirect:/question/detail/%s", id) ;
	}
	
	
	
	
	
	
	
}
