package com.jp.auth;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	private static final String SECRET_KEY = "455FEB2A514F81714485AE0176311EDCF36CEAD355C4980EA7C63ADC4D49F884E16368B9280014A291DA9E0724925FD572807460D4C0B576533FD998C7890684";

	public String generateToken(UserDetails userDetails) {

		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(Date.from(Instant.now()))
				.expiration(Date.from(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(30))))
				.signWith(getSecretKey())
				.compact();

	}

	private Key getSecretKey() {
		byte[] decode = Base64.getDecoder().decode(SECRET_KEY.getBytes());
		return Keys.hmacShaKeyFor(decode);
	}

}
