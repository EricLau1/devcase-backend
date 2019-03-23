package com.vsm.devcase.models;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NaturalId
	@Email
	@NotBlank
	@Column(nullable=false)
	@Size(max=40)
	private String email;
	
	@NotBlank
	@Column(nullable=false)
	@Size(max=60)
	private String password;
	
	@Column(columnDefinition="char(1) default '0'")
	private char status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp default current_timestamp()")
	private Calendar createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp default current_timestamp()")
	private Calendar updatedAt;
	
	public Admin() {
		this.status = '0';
	}
	
	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
		this.status = '0';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
}
