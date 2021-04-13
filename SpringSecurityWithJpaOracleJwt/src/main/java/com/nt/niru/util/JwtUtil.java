package com.nt.niru.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${app.secrete.key}")
	private String secret;
	//6.validate Token uname and database uname and validate token
	public boolean validateToken(String token,String username) {
		String tokenUsername=getSubject(token);
		return (username.equals(tokenUsername) && !isTokenExpire(token));
	}
	//5.validate Expiration
	public boolean isTokenExpire(String token) {
		Date expDate=getExpiryDate(token);
		//check this date is gone with Sytem date
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	//4.GetSubject/username
	public String getSubject(String token) {
		return getClaims(token).getSubject();
	}
	//3.Checkexpire date from claimes
	public Date getExpiryDate(String token) {
		return getClaims(token).getExpiration();
	}
	//2.read token
		public Claims getClaims(String token) {
		return	Jwts.parser()
			.setSigningKey(secret.getBytes())
			.parseClaimsJws(token)
			.getBody();
		}
	//1.Generate token
	public String generateToken(String subject) {
	return	Jwts.builder()
		.setSubject(subject)//Username
		.setIssuer("NARESHIT")
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
		.signWith(SignatureAlgorithm.HS256, secret.getBytes())
		.compact();
	}

}
