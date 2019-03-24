package com.vsm.devcase.models;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="decimal(10, 2)", nullable=false)
	private BigDecimal cashSpent;
	
	@ManyToOne(optional=false, cascade=CascadeType.REMOVE)
	private Customer customer;

	@Column(columnDefinition="int(5) default '0'")
	private Integer points;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp default current_timestamp()")
	private Calendar createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="timestamp default current_timestamp()")
	private Calendar updatedAt;
	
	public Purchase() {
		this.points = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCashSpent() {
		return cashSpent;
	}

	public void setCashSpent(BigDecimal cashSpent) {
		this.cashSpent = cashSpent;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
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
	

}
