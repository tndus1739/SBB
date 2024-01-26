package com.mysite.sbb.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import oracle.net.aso.DataIntegrityAlgorithm;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	// 회원가입 폼을 전송 : (주의)- th:object="${userCreateForm}"
	@GetMapping("/user/signup")  // 링크 걸어서 뷰페이지 보여줌
	public String signup (UserCreateForm userCreateForm) { // 매게변수 1개
	return "signup_form";
	}
	
	// 클라이언트가 회원가입 정보를 전송
	@PostMapping("/user/signup")
	public String signup (@Valid UserCreateForm userCreateForm , 
			BindingResult bindingResult,      // 오류가 발생하게 되면 오류정보를 bindingResult에 저장함
			Model model) {  // 매게변수 2개
//		System.out.println(userCreateForm.getUsername());
//		System.out.println(userCreateForm.getPassword1());
//		System.out.println(userCreateForm.getPassword2());
//		System.out.println(userCreateForm.getEmail());
		
		// 유효성체크에 오류가 발생되었을 때 signup_form.html에 그대로 머물면서 오류 코드를 출력
		if (bindingResult.hasErrors()) {
			
			return "signup_form";
		}
		
		// password1, password2 필드의 값이 같은지 확인 후 같으면
		if(! userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2","passwordInCorrect", 
								    	"두개의 패스워드가 일치하지 않습니다");
			
			return "signup_form";
		}
		
		
		// 유효성 검증을 통과하면 DB에 저장
		
		// username 필드에 중복된 값이 존재하면 오류 메세지를 출력함
		
		try {
		
		
		userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), 
				userCreateForm.getEmail());
		
		} catch (DataIntegrityViolationException e) {
			// DB컬럼의 무결성 제약조건 위반시 작동됨
			
			bindingResult.reject("signupFail", "이미 등록된 사용자입니다.");
			return "signup_form";
			
		} catch ( Exception e) {
			// 그 외의 예외 (오류)가 발생되면 작동
			bindingResult.reject("signupFailed", "알 수 없는 오류 발생");
			return "signup_form";
			
		}
		
		// 회원가입이 완료되면 "/"로 이동
		
		return "redirect:/";
		}
	
		// 로그인 처리 : Spring Security
		// 뷰페이지 전송
		
		@GetMapping("/user/login")
		public String login() {
			
			return "login_form";
		}
		
		// ★ 인증 처리는  Spring Security 에서 처리함
		//인증을 처리하는 @PostMappging("/user/login") <== 
		// SecurityConfig.java 에서 인증 정보를 받아서 처리함.
				
		
		
		 
		
}