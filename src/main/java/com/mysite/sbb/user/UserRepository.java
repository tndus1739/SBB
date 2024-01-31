package com.mysite.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<SiteUser, Long>{
	
	/*
	   select : findAll() , findById()
	   insert : save()
	   update : save()
	   delete : delete
	 
	 */

	// 인증처리를 위한 메소드 생성
		// select * from Site_User where username = ? ;
	Optional <SiteUser> findByUsername(String username);
	
	// 값이 중복되지 않을 때만 optional 에 담고 값이 여러개면 list에 담는다. 
	// optional은 값을 1개만 담을 수 있다. 
	
}
