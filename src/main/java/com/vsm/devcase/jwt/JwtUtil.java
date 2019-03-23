package com.vsm.devcase.jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	private static String key = "SUP3RS3CR3T";
	public static String ERROR_MESSAGE = "";
	public static boolean HAS_ERROR = false;
	
	public static String create(String payload) {
		return Jwts.builder()
				.setExpiration(new Date(System.currentTimeMillis() + (30 * 60 * 1000))) // 30 minutos 
				.setSubject(payload)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}

	public static Jws<Claims> decode(String token) {
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token);
	}
	
	public static boolean isValid(String token) {
		ERROR_MESSAGE = "";
		HAS_ERROR = false;
		try {
			if(!decode(token).getSignature().equals("")) {
	
				return true;
			}
		} catch (JwtException e) {
			ERROR_MESSAGE = e.getMessage();
			HAS_ERROR = true;
			return !HAS_ERROR;
		}
		return false;
	}
	
	public static void info(String token) {

		Jws<Claims> jws = decode(token);
		
		System.out.printf("\n\nToken => [Owner =>  %s, ", jws.getBody().getSubject());
		System.out.println("Expire => " + jws.getBody().getExpiration().toString() + " ]");
	}
	
	public static String getEmailByToken(String token) {
		Jws<Claims> jws = decode(token);
		return jws.getBody().getSubject();	
	}
}
