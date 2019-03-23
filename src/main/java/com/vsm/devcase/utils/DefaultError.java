package com.vsm.devcase.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vsm.devcase.jwt.JwtUtil;

public class DefaultError {

	public String message;
	public String status;
	
	public DefaultError() {
		
	}
	
	public DefaultError(String message, String status) {
		this.message = message;
		this.status = status;
	}
	
	public ResponseEntity<?> byEntity(HttpStatus code) {
		return new ResponseEntity<>(this, code);
	}
	
	public ResponseEntity<?> noAuth() {
		this.setMessage("Usuário não autorizado");
		this.setStatus("UNAUTHORIZED");
		if(JwtUtil.HAS_ERROR) {
			this.message = JwtUtil.ERROR_MESSAGE;
		}
		return new ResponseEntity<>(this, HttpStatus.UNAUTHORIZED);
	}
		
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
