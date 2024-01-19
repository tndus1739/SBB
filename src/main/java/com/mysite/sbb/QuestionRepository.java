package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer>{   //★★★★★ 중요함
	// 인터페이스 이름 짓는 방법 : Entity 클래스의 이름 + Repository
	
	// 상위 인터페이스의 모든 메소드가 상속 되어서 내려옴
	
/*	JpaRepository 에서 선언된 메소드가 상속되어 내려옴
	
	findAll() : select * from question
	save()    : insert, update
	delete()  : delete

*/

}
