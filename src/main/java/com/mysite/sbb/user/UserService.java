package com.mysite.sbb.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	// client --> controller --> service --> Repository
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	// SecurityConfig.java 에서 객체 등록됨
	
	
	// 사용자 정보를 추가하는 메소드 생성
	
	public SiteUser create(String username , String password , String email) {
		
		SiteUser user = new SiteUser () ;
		user.setUsername(username);
		user.setEmail(email);
		
		// password를 DB에 컬럼에 저장할 때 암호화해서 저장
		// 암호화 방식이 변경되면 문제가 발생될 수 있으니 여기에서 객체화하지 않는다. 
		// 암호화하는 객체생성은 Spring Security 에서 생성해서 가지고 와서 적용함

		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder() ;
		
		user.setPassword(passwordEncoder.encode(password)); // 암호화해서 처리됨
		
		// DB에 저장 
		userRepository.save(user);
		
		
		
		
		return user;
	}

}
