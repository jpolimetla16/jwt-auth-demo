package com.jp.users;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEntity> optUserEntity = userRepository.findByEmail(username);
		if(!optUserEntity.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		
		UserEntity userEntity = optUserEntity.get();
		return User.builder()
					.username(username)
					.password(userEntity.getPassword())
					.authorities(new ArrayList<>()).build();
		
	}
	
	
	
	

}
