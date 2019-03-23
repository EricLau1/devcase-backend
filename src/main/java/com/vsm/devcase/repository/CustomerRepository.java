package com.vsm.devcase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vsm.devcase.models.Customer;
import com.vsm.devcase.models.Gender;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	public Customer findById(long id);
	public List<Customer> findAllByOrderByIdAsc();
	
	public Page<Customer> findAll(Pageable pageable);
	public Page<Customer> findAllByGender(Gender gender, Pageable pageable);
}
