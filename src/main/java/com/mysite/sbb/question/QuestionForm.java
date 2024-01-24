package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
	
	// DTO : client 에서 넘어오는 파라미터의 값을 자동으로 주입 , 유효성 체크
	
	@NotEmpty (message = "제목은 필수 입력 사항 입니다.")
	@Size(max=200)
	private String subject;
	
	@NotEmpty (message = "내용은 필수 입력 사항 입니다.")
	private String content;
	
}
