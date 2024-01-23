package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	// 질문에 대한 답변을 저장하는 테이블
	// Question : 부모 , Answer : 자식 ( Question 테이블의 ID 컬럼을 참조해서 답변을 저장)
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer id;
	 
	 @Column(length = 20000)
	 private String content;
	 
	 private LocalDateTime createDate;  // 실제 컬럼이름 : create_date
	 
	 // Foreign Key : Answer 테이블은 Question 테이블을 참조함
	 // @ManyToOne : JPA에서 테이즐 간의 관계 설정 , 자식테이블(Answer)에서 부모테이블(Question)을 참조
	 // question 컬럼은 Question 테이블의 Primary Key의 값이 들어옴
	 // question 컬럼 이름이 question_id로 이름이 바뀜
	 
	 
	 //★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	 @ManyToOne  // Answer 테이블 : many / Question 테이블 : one
	 
	 private Question question;  // 중요★★★★★	 
	
}
