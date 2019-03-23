package com.vsm.devcase.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.middleware.BasicAuthentication;
import com.vsm.devcase.models.Customer;
import com.vsm.devcase.models.Gender;
import com.vsm.devcase.models.Purchase;
import com.vsm.devcase.repository.CustomerRepository;
import com.vsm.devcase.repository.PurchaseRepository;
import com.vsm.devcase.utils.DefaultError;
import com.vsm.devcase.utils.HandlerPoints;
import com.vsm.devcase.utils.Pagination;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins="*")
public class PurchaseController {

	@Autowired
	private PurchaseRepository pr;
	
	@Autowired
	private CustomerRepository cr;
	
	@PostMapping("/purchases/customer/{customerId}")
	public ResponseEntity<?> postPurchase(@RequestBody Purchase purchase, 
			@PathVariable(value="customerId") long customerId, HttpServletRequest req) {
	
		if(BasicAuthentication.isAuth(req)) {
			
			Customer customer = cr.findById(customerId);

			if(customer != null) {
				purchase.setCustomer(customer);
				purchase.setPoints(HandlerPoints.define(purchase.getCashSpent().doubleValue()));
				return new ResponseEntity<>(pr.save(purchase), HttpStatus.CREATED);
			}
			
			return new ResponseEntity<>(new DefaultError("Cliente não existe", "UNPROCESSABLE ENTITY"), 
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new DefaultError().noAuth();
		
	}
	
	@GetMapping("/purchases")
	public ResponseEntity<?> getPurchases(@RequestParam("page") Optional<Integer> page,
			HttpServletRequest req) {
		
		if(BasicAuthentication.isAuth(req)) {
			if(page.isPresent()) {
				System.out.printf("\nPage => %d\n", page.get().intValue());
				return new ResponseEntity<>(pr.findAll(Pagination.paginate(page.get().intValue(), "id")), 
						HttpStatus.OK);
			}
			return new ResponseEntity<>(pr.findAll(Pagination.paginate(0, "id")), HttpStatus.OK);
		}
		return new DefaultError().noAuth();
	}
	
	@GetMapping("/purchases/{id}")
	public ResponseEntity<?> getPurchase(@PathVariable(value="id") long id,
			HttpServletRequest req) {
		
		if(BasicAuthentication.isAuth(req)) {
			return new ResponseEntity<>(pr.findById(id), HttpStatus.OK);
		}
		return new DefaultError().noAuth();
	}
	
	@GetMapping("/purchases/gender/{gender}")
	public ResponseEntity<?> getPurchases(@PathVariable(value="gender") Gender gender, 
			@RequestParam("page") Optional<Integer> page, HttpServletRequest req) {
		
		if(BasicAuthentication.isAuth(req)) {
			if(page.isPresent()) {
				
				return new ResponseEntity<>(pr.findAllByCustomerByGender(gender, 
						Pagination.paginate(page.get().intValue(), "id")), 
						HttpStatus.OK);
			} 
			return new ResponseEntity<>(pr.findAllByCustomerByGender(gender, Pagination.paginate(0, "id")), 
					HttpStatus.OK);
		}
		return new DefaultError().noAuth();
	}
}
