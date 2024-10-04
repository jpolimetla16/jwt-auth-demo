package com.jp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthController {
	
	@Autowired
	private JwtAuthService jwtAuthService;
	
	@PostMapping("/authenticate")
	public String authenticateAndGenerateJwtToken(@RequestBody LoginRequestDto loginRequestDto) {
		String jwtToken =  jwtAuthService.authenticate(loginRequestDto);
		return jwtToken;
	}

}
