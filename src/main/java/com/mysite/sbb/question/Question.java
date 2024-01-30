package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	
	//질문정보를 저장하는 테이블 
	
	// @Entity : DB의 테이블과 매핑
	// Question : 테이블명
	// 필드 : 테이블의 컬럼과 연결
	
	// @Id : 필드 위에 할당 , Primary Key ( 중복된 값을 못 넣도록 설정) , 테이블에 반드시 1개가 적용되어야 함
	// @GeneratedValue  : 자동으로 값을 증가해서 생성해줌 , @Id와 같이 부여
	// @Column : 컬럼의 제약 사항을 적용
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer id; // 필드 ( id : 필드는 Primary Key 선언)
	
	@Column(length=200)
	private String subject;
	
	@Column(length=20000)
	private String content;
	private LocalDateTime createDate;  // [질문 생성 날짜] 변수를 camal case 로 입력해도 실제 DB에는 create_date ★ 
	private LocalDateTime modifyDate;  // 질문을 수정한 날짜
	
	
	// 질문 (Question)  : 1 이라면, 질문에 대한 답변 (Answer) : 多 ( 1:多 관계 )
	// 하나의 질문에 대해서 모든 답변을 가져올 수 있다
	
	// cascade=CascadeType.REMOVE : 질문이 제거될 때 질문에 해당하는 모든 답변을 함께 제거함
	@OneToMany(mappedBy = "question" , cascade=CascadeType.REMOVE) 
	private List<Answer> answerList; 

	// 글쓴 사용자 정보 컬럼 추가함. 
	// FK : author_id
	// SiteUser : 부모 , Question : 자식 
	@ManyToOne
	private SiteUser author; 

}
