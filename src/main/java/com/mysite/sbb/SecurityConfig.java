package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
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
		
		http
			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
			.requestMatchers (new AntPathRequestMatcher("/**")).permitAll());
		
		return http.build();
		
		
	}
	
}
