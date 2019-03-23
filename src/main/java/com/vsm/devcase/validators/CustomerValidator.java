package com.vsm.devcase.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vsm.devcase.models.Customer;

public class CustomerValidator {
	
	private ValidatorUtil validator = new ValidatorUtil();

	public Customer validate(Customer c) {
			
		validator.setHasErrors(!this.requiredAttributes(c));
		validator.setHasErrors(!this.minAttributes(c));
		validator.setHasErrors(!this.maxAttributes(c));
		
		if(!this.isEmail(c.getEmail())) {
			validator.getErrorMessages().add("Email inválido");
			validator.setErrors(validator.getErrors() + 1);
		}
		
		if(validator.isHasErrors()) {
			return null;
		}
		validator.setValid(true);
		
		return c;
	}
	
	
	public ValidatorUtil getValidator() {
		return validator;
	}



	public void setValidator(ValidatorUtil validator) {
		this.validator = validator;
	}

	public boolean requiredAttributes(Customer c) {
		if(!validator.required(c.getFirstName())) {
			validator.getErrorMessages().add("Nome requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.required(c.getLastName())) {
			validator.getErrorMessages().add("Sobrenome requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.required(c.getPhone())) {
			validator.getErrorMessages().add("Telefone requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.required(c.getCpf())) {
			validator.getErrorMessages().add("CPF requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.required(c.getRg())) {
			validator.getErrorMessages().add("Rg requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.required(c.getEmail())) {
			validator.getErrorMessages().add("Email requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.required(c.getAddress())) {
			validator.getErrorMessages().add("Endereço requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.required(c.getGender().toString())) {
			validator.getErrorMessages().add("Gênero requerido");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(validator.getErrorMessages().size() > 0) {
			return false;
		}
		return true;
	}
	
	public boolean minAttributes(Customer c) {
		if(!validator.minLength(c.getPhone(), 11)) {
			validator.getErrorMessages().add("Telefone: Mínimo 11 números");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.minLength(c.getCpf(), 11)) {
			validator.getErrorMessages().add("CPF: Mínimo 11 números");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.minLength(c.getRg(), 9)) {
			validator.getErrorMessages().add("RG: Mínimo 9 números");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(validator.getErrorMessages().size() > 0) {
			return false;
		}
		return true;
	}
	
	public boolean maxAttributes(Customer c) {
		if(!validator.maxLength(c.getFirstName(), 35)) {
			validator.getErrorMessages().add("Nome: Máximo 35 caracteres");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.maxLength(c.getLastName(), 35)) {
			validator.getErrorMessages().add("Sobrenome: Máximo 35 caracteres");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.maxLength(c.getPhone(), 15)) {
			validator.getErrorMessages().add("Telefone: Máximo 15 caracteres");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.maxLength(c.getCpf(), 14)) {
			validator.getErrorMessages().add("CPF: Máximo 14 caracteres");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.maxLength(c.getRg(), 12)) {
			validator.getErrorMessages().add("RG: Máximo 12 caracteres");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.maxLength(c.getEmail(), 40)) {
			validator.getErrorMessages().add("Email: Máximo 40 caracteres");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.maxLength(c.getAddress(), 50)) {
			validator.getErrorMessages().add("Endereço: Máximo 50 caracteres");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(!validator.maxLength(c.getGender().toString(), 1)) {
			validator.getErrorMessages().add("Gênero: Máximo 1 caractere");
			validator.setErrors(validator.getErrors() + 1);
		}
		if(validator.getErrorMessages().size() > 0) {
			return false;
		}
		return true;
	}
	
	public boolean isEmail(String email) {
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
}
