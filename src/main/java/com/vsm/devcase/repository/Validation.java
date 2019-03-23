package com.vsm.devcase.repository;

public interface Validation {
	
	public boolean required(String attribute);
	public boolean maxLength(String attribute, int max);
	public boolean minLength(String attribute, int min);
	
}
