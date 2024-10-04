package com.jp.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByEmail(String email);

}
