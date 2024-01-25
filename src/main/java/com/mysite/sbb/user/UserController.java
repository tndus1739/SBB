package com.mysite.sbb.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

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
	public String signup (UserCreateForm userCreateForm , Model model) {  // 매게변수 2개
		System.out.println(userCreateForm.getUsername());
		System.out.println(userCreateForm.getPassword1());
		System.out.println(userCreateForm.getPassword2());
		System.out.println(userCreateForm.getEmail());
		
		// 회원가입이 완료되면 "/"로 이동
		return "redirect:/";
	}
	

	
}