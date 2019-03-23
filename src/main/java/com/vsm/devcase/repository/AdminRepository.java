package com.vsm.devcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vsm.devcase.models.Admin;

public interface AdminRepository  extends JpaRepository<Admin, Long>{

	public Admin findFirstAdminByEmail(String email);
	
}
