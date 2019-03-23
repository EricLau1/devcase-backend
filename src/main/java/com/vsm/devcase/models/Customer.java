package com.vsm.devcase.models;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable=false)
	@Size(max=35)
	private String firstName;
	
	@NotBlank
	@Column(nullable=false)
	@Size(max=35)
	private String lastName;
	
	@NotBlank
	@Column(nullable=false)
	@Size(max=50)
	private String address;
	
	@NotBlank
	@Column(nullable=false)
	@Size(max=15) // (00) 00000-0000
	private String phone;
	
	@NotBlank
	@Column(nullable=false, unique=true)
	@Size(max=14) // 000.000.000-00
	private String cpf;
	
	@NotBlank
	@Column(nullable=false, unique=true)
	@Size(max=12) // 00.000.000-0
	private String rg;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition="enum('M', 'F')")
	private Gender gender;
	
	@NotBlank
	@Email
	@Column(nullable=false, unique=true)
	@Size(max=40)
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp default current_timestamp()")
	private Calendar createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp default current_timestamp()")
	private Calendar updatedAt;
	
	@JsonIgnore
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action=OnDeleteAction.CASCADE)
	List<Purchase> purchases;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	@JsonIgnore
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	public String toJson() {
		return "{\n" +
				"\t\"firstName\":\t\"" + this.getFirstName() + "\"\n" +
				"\t\"lastName\":\t\"" + this.getLastName() + "\"\n" +
				"\t\"address\":\t\"" + this.getAddress() + "\"\n" +
				"\t\"phone\":\t\"" + this.getPhone() + "\"\n" +
				"\t\"cpf\":\t\"" + this.getCpf() + "\"\n" + 
				"\t\"rg\":\t\"" + this.getRg() + "\"\n" +
				"\t\"email\":\t\"" + this.getEmail() + "\"\n" + 
				"\t\"gender\":\t\"" + this.getGender() + "\"\n}\n";
	}

}
