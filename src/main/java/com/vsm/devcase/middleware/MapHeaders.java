package com.vsm.devcase.middleware;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class MapHeaders {

	public void showHeaders(HttpServletRequest request) {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    Enumeration<String> headerNames = httpRequest.getHeaderNames();

	    if (headerNames != null) {
	    		System.out.println("");
	            while (headerNames.hasMoreElements()) {
	            		String headerName = (String) headerNames.nextElement();
	            		String headerValue = httpRequest.getHeader(headerName);
	                    System.out.printf("%s: %s\n", headerName, headerValue);
	            }
	    }
	}
}
