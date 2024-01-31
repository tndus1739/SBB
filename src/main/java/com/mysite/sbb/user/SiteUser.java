package com.mysite.sbb.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class SiteUser {
	
	// Entity Class : DB의 테이블과 매핑
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private Long id ;
	
	@Column (unique = true) // (unique = true) : 중복된 값을 넣을 수 가 없다. 
	private String username ;
	
	private String password ;
	
	@Column (unique = true)
	private String email ;
}
