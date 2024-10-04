package com.jp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository repository;
	
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody UserEntity userEntity){
		String password = userEntity.getPassword();
		userEntity.setPassword(bCryptPasswordEncoder.encode(password));
		UserEntity savedUser = repository.save(userEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}
	

}
