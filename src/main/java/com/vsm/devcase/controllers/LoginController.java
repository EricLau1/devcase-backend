package com.vsm.devcase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.jwt.JwtUtil;
import com.vsm.devcase.jwt.Token;
import com.vsm.devcase.models.Admin;
import com.vsm.devcase.repository.AdminRepository;
import com.vsm.devcase.security.Password;
import com.vsm.devcase.utils.DefaultError;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins="*")
public class LoginController {

	@Autowired
	private AdminRepository ar;
	
	@PostMapping("/login")
	public ResponseEntity<?> signIn(@RequestBody Admin login) {
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
		
		Admin admin = ar.findFirstAdminByEmail(login.getEmail());

		if(admin != null && Password.verify(login.getPassword(), admin.getPassword())) {
			Token token = new Token();
			token.setToken(JwtUtil.create(admin.getEmail()));
			return  new ResponseEntity<>(token, headers, HttpStatus.OK);
		}
		return  new ResponseEntity<>(new DefaultError("Login inválido", "UNAUTHORIZED"),
				headers, HttpStatus.UNAUTHORIZED);
	}
}
