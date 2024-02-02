package com.mysite.sbb.answer;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.mysite.sbb.question.Question;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	
	/*
	 
	 	★ Repository : DB의 테이블을 CRUC 메소드
	 
	 	findAll();   --> select * from answer ;
	 		List<Answer> list = answerRepository.findAll();
	 		
	 	findById();  --> select * from answer where id = ? ;
	 		Optional<Answer> op = answerRepository.findById(1);
	 		
	 	save(a1) : insert, update
	 	delete(a1) : delete
	 
	  */
	
	// 이 외의 다른 컬럼의 조건으로 검색할 경우 메소드를 만들어야 함
	
	// content 컬럼의 내용을 검색 : like
	// select * from Answer where content like '%?%';
		List<Answer>findByContentLike (String content);


	// pageable  : 페이징 처리하는 인터페이스
		
	Page<Answer> findAll(Pageable pageable);
		@Query("select "
	            + "distinct q "
	            + "from Question q " 
	            + "left outer join SiteUser u1 on q.author=u1 "
	            + "left outer join Answer a on a.question=q "
	            + "left outer join SiteUser u2 on a.author=u2 "
	            + "where "
	            + "   q.subject like %:kw% "
	            + "   or q.content like %:kw% "
	            + "   or u1.username like %:kw% "
	            + "   or a.content like %:kw% "
	            + "   or u2.username like %:kw% ")
		Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);  // 위의 파란 글씨와 함께 사용 (이제 이거 사용)

		// 인수

}
