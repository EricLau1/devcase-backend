package com.vsm.devcase.middleware;

import javax.servlet.http.HttpServletRequest;

import com.vsm.devcase.jwt.JwtUtil;

public class BasicAuthentication {

	private static final String AUTHORIZATION = "authorization";

	public static boolean isAuth(HttpServletRequest req) {
		if(req.getHeader(AUTHORIZATION) != null) {
			
			String token = extractTokenHeader(req);
		
			if(JwtUtil.isValid(token)) {
				return true;
			}

		}
		return false;
	}

	public static String extractTokenHeader(HttpServletRequest req) {
		if(req.getHeader(AUTHORIZATION) != null) {
			String[] headerSplit = req.getHeader(AUTHORIZATION).split(" ", 2);
			return headerSplit[1];		
		}
		return "";
	}

	public static void info(HttpServletRequest req) {
		if (isAuth(req)) {
			String token = BasicAuthentication.extractTokenHeader(req);
			String owner = JwtUtil.getEmailByToken(token);
			JwtUtil.info(token);
			System.out.println("\n");
			System.out.print("\"Auth\": {\n" + "\t\"is_valid\":\t" + BasicAuthentication.isAuth(req) + ",\n"
					+ "\t\"token\":\t" + token + ",\n" + "\t\"owner\":\t" + owner + "\n" + "}\n\n");
			return;
		}
		System.out.println("\nNo Token...");
	}

}
