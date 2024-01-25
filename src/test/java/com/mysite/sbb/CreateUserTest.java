package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

@SpringBootTest
public class CreateUserTest {
	
	@Autowired // : 필드위에서 객체주입
	private UserService userService;
	
	@Test
	public void createUser() {
		
		
		SiteUser user = userService.create("user1", "1234", "aaa@aaa.com");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		
	}
	
}
