package com.vsm.devcase.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.models.Gender;
import com.vsm.devcase.utils.GenerateData;


@RestController
@RequestMapping("/v1/api")
public class DataloadController {

	@GetMapping("/dataload/random")
	public ResponseEntity<?> dataload() {
		return new ResponseEntity<>(GenerateData.getRandomCustomer(), HttpStatus.OK);
	}
	
	@GetMapping("/dataload/random/{gender}")
	public ResponseEntity<?> getFemaleCustomer(@PathVariable(value="gender") Gender gender) {
		if(gender.equals(Gender.M)) {
			return new ResponseEntity<>(GenerateData.getRandomMale(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(GenerateData.getRandomFemale(), HttpStatus.OK);
	}
	
}
