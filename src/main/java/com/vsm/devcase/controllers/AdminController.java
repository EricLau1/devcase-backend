package com.vsm.devcase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.models.Admin;
import com.vsm.devcase.repository.AdminRepository;
import com.vsm.devcase.security.Password;

@RestController
@RequestMapping(value="/v1/api")
@CrossOrigin(origins="*")
public class AdminController {

	@Autowired
	private AdminRepository ar;
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> postAdmin(@RequestBody Admin admin) {
		admin.setPassword(Password.hash(admin.getPassword()));
		Admin registeredAdmin = ar.save(admin);
		return  new ResponseEntity<>(registeredAdmin, HttpStatus.CREATED);
	}
}
