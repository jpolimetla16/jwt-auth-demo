package com.jp.users;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "455FEB2A514F81714485AE0176311EDCF36CEAD355C4980EA7C63ADC4D49F884E16368B9280014A291DA9E0724925FD572807460D4C0B576533FD998C7890684";
	
	
	public String extractUsername(String jwtToken) {
		Claims claims = getClaims(jwtToken);
		return claims.getSubject();
	}


	private Claims getClaims(String jwtToken) {
		Claims claims = Jwts.parser()
		      .verifyWith(getSecretKey())
		      .build()
		      .parseSignedClaims(jwtToken)
		      .getPayload();
		return claims;
	}
	
	
	private SecretKey getSecretKey() {
		byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(decodedKey);
	}


	public boolean isValidToken(String jwtToken) {
		Claims claims = getClaims(jwtToken);
		return claims.getExpiration().after(Date.from(Instant.now()));
	}
	
	

}
