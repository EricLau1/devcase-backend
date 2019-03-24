package com.vsm.devcase.controllers;

import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.middleware.BasicAuthentication;
import com.vsm.devcase.models.Customer;
import com.vsm.devcase.models.Gender;
import com.vsm.devcase.repository.CustomerRepository;
import com.vsm.devcase.utils.DefaultError;
import com.vsm.devcase.utils.Pagination;
import com.vsm.devcase.validators.CustomerValidator;

@RestController
@RequestMapping(value="/v1/api")
@CrossOrigin(origins="*")
public class CustomerController {

	@Autowired
	private CustomerRepository cr;
	
	@GetMapping("/customers")
	public ResponseEntity<?> getCustomers(HttpServletRequest req,
			@RequestParam("page") Optional<Integer> page) {
		
		if(BasicAuthentication.isAuth(req)) {
			
			if(page.isPresent()) {
				return new ResponseEntity<>(cr.findAll(Pagination.paginate(page.get().intValue(), "id")), HttpStatus.OK);
			}
		
			return new ResponseEntity<>(cr.findAll(Pagination.paginate(0, "id")), HttpStatus.OK);		
		}
		
		return new DefaultError().noAuth();

	}
	
	@GetMapping("/customers/gender/{gender}")
	public ResponseEntity<?> getCustomersByGender(@PathVariable(value="gender") Gender gender,
			@RequestParam("page") Optional<Integer> page, HttpServletRequest req) {
		
		if(BasicAuthentication.isAuth(req)) {
			if(page.isPresent()) {
				return new ResponseEntity<>(cr.findAllByGender(gender, Pagination.paginate(page.get().intValue(), "id")), HttpStatus.OK);
			}
		
			return new ResponseEntity<>(cr.findAllByGender(gender, Pagination.paginate(0, "id")), HttpStatus.OK);
		}
		return new DefaultError().noAuth();

	}
	
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> getCustomers(@PathVariable(value="id") long id, HttpServletRequest req) {
		if(BasicAuthentication.isAuth(req)) {
			Customer customer = cr.findById(id);
			return new ResponseEntity<>(customer, HttpStatus.OK);
		}
		return new DefaultError().noAuth();
	}
	
	@PostMapping("/customers")
	public ResponseEntity<?> postCustomer(@RequestBody Customer customer, HttpServletRequest req) {
		
		if(BasicAuthentication.isAuth(req)) {
			
			CustomerValidator validation = new CustomerValidator();
			if(validation.validate(customer) != null) {
			
				Customer registeredCustomer = cr.save(customer);
				return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
				
			}
			return new ResponseEntity<>(validation.getValidator(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new DefaultError().noAuth();

	}
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<?> putCustomer(@PathVariable(value="id") Long id,
			@RequestBody Customer customer, HttpServletRequest req) {
		
		if(BasicAuthentication.isAuth(req)) {
			
			CustomerValidator validation = new CustomerValidator();
			if(validation.validate(customer) != null) {
				
				customer.setId(id);
				customer.setUpdatedAt(Calendar.getInstance());
				return new ResponseEntity<>(cr.save(customer), HttpStatus.OK);
				
			}
			return new ResponseEntity<>(validation.getValidator(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new DefaultError().noAuth();

	}
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(value="id") long id, HttpServletRequest req) {
		
		if(BasicAuthentication.isAuth(req)) {
			cr.deleteById(id);
			return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
		}
		
		return new DefaultError().noAuth();

	}
}
