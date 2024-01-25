package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreateForm {

	// client의 값을 받아서 validation하는 객체
	
	@Size(min=3, max =25)
	@NotEmpty(message = "사용자 ID는 필수 입력 사항입니다.")
	private String username;
	
	@NotEmpty(message = "패스워드는 필수 입력 사항입니다.")
	private String password1;

	@NotEmpty(message = "패스워드는 필수 입력 사항입니다.")
	private String password2;
	
	@NotEmpty(message = "메일주소는 필수 입력 사항입니다.")
	@Email
	private String email;
	
	
}
