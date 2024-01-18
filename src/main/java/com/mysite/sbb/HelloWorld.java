package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // 스프링에서는 어노테이션들이 많음  ( @ 누르고 con입력하고 ctrl + space) 
public class HelloWorld {
	
	//http://localhost:8585/hello ( url로 요청을 보내는 것 : getmapping )
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		return "Hello World - Spring Boot";  // "/hello" 요청에 의해서 이메소드가 작성이 됨
		
	}

	// http://localhost:8585/my
	@GetMapping("/my")
	@ResponseBody
	public String myPage() {
		
		return "첫 번째 페이지입니다★★★";
	}

	@GetMapping("/abcd")
	@ResponseBody // (문자열 자체를 return으로 돌려줌)
	public String myPage2() {
		
		return "abcd 요청에 대한 답";
	}

	// 요청에 대한 뷰 페이지를 클라이언트로 전송 (@ResponseBody 생략 , @ResponseBody이 없으면 문서를 보여줌)
	// abc.html 을 전송 : template 폴더에 존재해야 함
	// http://localhost:8585/abcdef
	
	@GetMapping("/abcdef")
	//@ResponseBody (@ResponseBody이 없으면 문서를 보여줌)
	public String view() {
		
		return "abc";
	}

	// Thymeleaf 라이브러리 설치 후 요청시 뷰 페이지 전송
	
	// http://localhost:8585/defg
	@GetMapping("/defghi")
	
	public String view2() {
		
		// 클라이언트 요청에 대해서 비즈니스 로직을 처리
		// 마지막으로 view 페이지를 전송
		
		return "def";
	}




}
