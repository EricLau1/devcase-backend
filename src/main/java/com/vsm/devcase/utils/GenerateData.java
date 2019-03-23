package com.vsm.devcase.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vsm.devcase.models.Customer;
import com.vsm.devcase.models.Gender;

public class GenerateData {

	private static Random random = new Random();
	private static List<Customer> females = new ArrayList<>();
	private static List<Customer> males = new ArrayList<>();

	public static void show() {
		
		System.out.printf("Phone...: %s\n", generatePhone());
		System.out.printf("CPF.....: %s\n", generateCpf());
		System.out.printf("RG......: %s\n", generateRg());
		System.out.printf("Name....: %s\n", generateFullName());
		System.out.printf("Street..: %s\n", generateStreet());
		System.out.printf("City....: %s\n", generateCity());
		System.out.printf("Address.: %s\n", generateFullAddress());
		
	}
	
	public static Customer getRandomFemale() {
		
		String[] girls = DataUtil.getFemaleNames();
		
		for(int i = 0; i < girls.length; i++) {
			Customer c = new Customer();
			c.setFirstName(girls[random.nextInt(girls.length)]);
			c.setLastName(GenerateData.generateLastName());
			c.setEmail((c.getFirstName() + c.getLastName()).toLowerCase() + "@email.com.br");
			c.setGender(Gender.F);
			c.setPhone(GenerateData.generatePhone());
			c.setCpf(GenerateData.generateCpf());
			c.setRg(GenerateData.generateRg());
			c.setAddress(GenerateData.generateFullAddress());
			females.add(c);
			
		}

		return females.get(random.nextInt(females.size()));
	}
	
	public static Customer getRandomMale() {
		
		String[] boys = DataUtil.getMaleNames();
		
		for(int i = 0; i < boys.length; i++) {
			Customer c = new Customer();
			c.setFirstName(boys[random.nextInt(boys.length)]);
			c.setLastName(GenerateData.generateLastName());
			c.setEmail((c.getFirstName() + c.getLastName()).toLowerCase() + "@email.com.br");
			c.setGender(Gender.M);
			c.setPhone(GenerateData.generatePhone());
			c.setCpf(GenerateData.generateCpf());
			c.setRg(GenerateData.generateRg());
			c.setAddress(GenerateData.generateFullAddress());
			males.add(c);	
		}
		return males.get(random.nextInt(males.size()));
	}
	
	
	public static Customer getRandomCustomer() {
		
		List<Customer> customers = new ArrayList<>();
		
		String[] girls = DataUtil.getFemaleNames();
		String[] boys = DataUtil.getMaleNames();
		
		for(int i = 0; i < girls.length; i++) {
			Customer c = new Customer();
			c.setFirstName(girls[i]);
			c.setLastName(GenerateData.generateLastName());
			c.setEmail((c.getFirstName() + c.getLastName()).toLowerCase() + "@email.com.br");
			c.setGender(Gender.F);
			c.setPhone(GenerateData.generatePhone());
			c.setCpf(GenerateData.generateCpf());
			c.setRg(GenerateData.generateRg());
			c.setAddress(GenerateData.generateFullAddress());
			females.add(c);
			customers.add(c);
			
		}
		
		for(int i = 0; i < boys.length; i++) {
			Customer c = new Customer();
			c.setFirstName(boys[i]);
			c.setLastName(GenerateData.generateLastName());
			c.setEmail((c.getFirstName() + c.getLastName()).toLowerCase() + "@email.com.br");
			c.setGender(Gender.M);
			c.setPhone(GenerateData.generatePhone());
			c.setCpf(GenerateData.generateCpf());
			c.setRg(GenerateData.generateRg());
			c.setAddress(GenerateData.generateFullAddress());
			males.add(c);
			customers.add(c);
			
		}
		
		return customers.get(random.nextInt(customers.size()));
		
	}
		
	public static String generatePhone() {
		String phone = "(" + randomNumber(10, 89) + ") ";
		phone += randomNumber(10000, 89999) + "-";
		phone += randomNumber(1000, 8999);
		return phone;
	}
	
	public static String generateCpf() {
		return randomNumber(100, 899) + "." +
				randomNumber(100, 899) + "." +
				randomNumber(100, 899) + "-" + 
				randomNumber(10, 89);
	}
	
	public static String generateRg() {
		return randomNumber(10, 89) + "." +
				randomNumber(100, 899) + "." +
				randomNumber(100, 899) + "-" + 
				randomNumber(0, 9);
	}
	
	public static String generateName() {
		String[] names = {};
		return names[random.nextInt(names.length)];
	}
	
	public static String generateLastName() {
		String[] lastNames = DataUtil.getLastNames();
		return lastNames[random.nextInt(lastNames.length)];
	}
	
	public static String generateFullName() {
		return generateName() + " " + generateLastName();
	}
	
	public static String generateCity() {
		String[] cities = DataUtil.getCityNames();
		return cities[random.nextInt(cities.length)];
	}
	
	public static String generateStreet() {
		String[] street = DataUtil.getStreetNames();
		return "Rua " + street[random.nextInt(street.length)] + ", " + 
				randomNumber(10, 9999);
	}
	
	public static String generateFullAddress() {
		return generateStreet() + " - " + generateCity();
	}
	
	public static String randomNumber(int min, int max) {
		return "" + (min + random.nextInt(max));
  	}
	


}
