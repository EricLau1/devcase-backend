package com.vsm.devcase.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.info.Author;
import com.vsm.devcase.info.Info;
import com.vsm.devcase.info.Route;
import com.vsm.devcase.info.Social;

@RestController
@CrossOrigin(origins="*")
public class GuideController {

	@GetMapping("/")
	public ResponseEntity<?> getInfo() {
		Info info = new Info();
		info.setDescription("VSM Devcase BackEnd");
		info.setVersion("1.0");
		Author author = new Author();
		author.setName("Eric Lau de Oliveira");
		author.setContacts(new Social("https://github.com/EricLau1", 
				"ericlau.oliveira@gmail.com", 
				"https://ericlau1.github.io/",
				"https://www.youtube.com/channel/UCr_3nxsd5v6g980xNese71w"));
		info.setAuthor(author);
		info.setRoutes(Route.defaultRoutes());
		return new ResponseEntity<>(info, HttpStatus.OK);
	}
	
}
