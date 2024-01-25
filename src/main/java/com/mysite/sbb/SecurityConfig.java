package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/* Spring Boot Security 설정을 만들어줌
		- 클래스 상단에 : @EnableWebSecurity  :  <--client <-- HTTP <-- Server
		- 객체가 Spring에 Bean 등록 되어야 함 : @Configuration
		- 필터체인 : 
		
		
	*/
	
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception {  // HttpSecurity가 return 값으로 들어옴
		
		//root : http://localhost:8585/  : / (root)요청은 인증없이 접근을 허용하겠다는 설정
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					.requestMatchers (new AntPathRequestMatcher("/**")).permitAll())     
		
		// http:// localhost:8585/h2-console : csrf  보안키 없이도 접근을 허용
		.csrf( (csrf) -> csrf
				.ignoringRequestMatchers( new AntPathRequestMatcher ("/h2-console/**"))
				
				)
		
		// 웹페이지의 프레임을 사용할 수 있도록 허용
		.headers( (headers) -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
				)
		;
		
		return http.build();
	}
	
		// 암호화 객체 생성 후 Spring 프레임뭐크에 객체를 등록함
		
		@Bean   
			PasswordEncoder passwordEncoder() {
				
				return new BCryptPasswordEncoder();
			}
		
	
	
	
}
