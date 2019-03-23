package com.vsm.devcase.validators;

import java.util.ArrayList;
import java.util.List;

import com.vsm.devcase.repository.Validation;

public class ValidatorUtil implements Validation {
	
	private List<String> errorMessages;
	private boolean hasErrors;
	private int errors;
	private boolean isValid;
	
	public ValidatorUtil() {
		this.errorMessages = new ArrayList<>();
		this.hasErrors = false;
		this.errors = 0;
		this.isValid = false;		
	}
	

	@Override
	public boolean required(String attribute) {
		if(attribute.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean maxLength(String attribute, int max) {
		if(attribute.length() > max) {
			return false;
		}
		return true;
	}

	@Override
	public boolean minLength(String attribute, int min) {
		if(attribute.length() < min) {
			return false;
		}
		return true;
	}


	public List<String> getErrorMessages() {
		return errorMessages;
	}


	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}


	public boolean isHasErrors() {
		return hasErrors;
	}


	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}


	public int getErrors() {
		return errors;
	}


	public void setErrors(int errors) {
		this.errors = errors;
	}


	public boolean isValid() {
		return isValid;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	public String toJson() {
		String json = "{\n";
		if(this.getErrorMessages().size() > 0) {
			json += "\t\"errorMessages\": [\n";
			for(String message: this.getErrorMessages()) {
				json += "\t\t\"" + message + "\",\n";
			}
			json += "\n\t]\n";
		}
		json += "\t\"hasError\":\t" + this.isHasErrors() + ",\n";
		json += "\t\"errors\":\t" + this.getErrors() + ",\n";
		json += "\t\"isValid\":\t" + this.isValid() + "\n}";
		return json;
	}
}
