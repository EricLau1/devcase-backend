package com.vsm.devcase.security;

import org.mindrot.jbcrypt.BCrypt;

public class Password {

	public static String hash(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean verify(String password, String hashed) {
		return BCrypt.checkpw(password, hashed);
	}
}
