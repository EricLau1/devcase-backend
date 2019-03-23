package com.vsm.devcase.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.vsm.devcase.models.Gender;
import com.vsm.devcase.models.Purchase;

public interface PurchaseRepository extends PagingAndSortingRepository<Purchase, Long> {

	public Purchase findById(long id);
	public List<Purchase> findAllByOrderByIdAsc();
	public Page<Purchase> findAll(Pageable pageable);
	
	@Query("select p from Purchase as p inner join Customer as c on p.customer = c where c.gender = :gender")
	public Page<Purchase> findAllByCustomerByGender(@Param("gender") Gender gender, Pageable pageable);
}
