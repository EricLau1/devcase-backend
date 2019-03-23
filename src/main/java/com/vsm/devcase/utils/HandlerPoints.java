package com.vsm.devcase.utils;


public class HandlerPoints {
	
	public static int define(double value) {		
		int points = 0;
		int bonus = 1; 
		for(double i = value; i > 0; i -= 50) {
			points += 10 * bonus;
			bonus++;
		}
		return points;
	}

}
