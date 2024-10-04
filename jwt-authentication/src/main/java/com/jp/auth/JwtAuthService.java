package com.jp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthService {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	public String authenticate(LoginRequestDto loginRequestDto) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),
					loginRequestDto.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		if(!authentication.isAuthenticated()) {
			throw new UsernameNotFoundException(loginRequestDto.getEmail());
		}
		
		return jwtUtil.generateToken(userDetailsServiceImpl.loadUserByUsername(loginRequestDto.getEmail()));
		
		
	}

}
