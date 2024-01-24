package com.mysite.sbb.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
	
	@NotEmpty(message = "답변을 입력해주세요") // 비어있지 않아야 함
	private String content;

}
