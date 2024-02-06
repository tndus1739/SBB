package com.mysite.sbb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//특별한 오류 코드에 대해서 처리하도록 지정함 
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Entity Not Found")

public class DataNotFoundException extends RuntimeException{

	/* 예외(프로그래머가 처리할 수 있는 오류,  
 	- 컴파일 체크 예외  : 
	- 런타임 체크 예외  : RuntimeException
*/

		//생성자 에 오류 메세지를 받아서 
			public DataNotFoundException(String message) {
				super(message);  
}
	
	
}
