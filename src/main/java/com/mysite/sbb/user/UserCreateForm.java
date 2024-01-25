package com.mysite.sbb.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateForm {

	// client의 값을 받아서 validation하는 객체
	
	private String username;
	
	private String password1;

	private String password2;
	
	private String email;
	
	
}
