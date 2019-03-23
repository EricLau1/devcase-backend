package com.vsm.devcase.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Pagination {
	
	public static final int LIMIT = 5;
	
	public static Pageable paginate(int page, String orderParam) {
		page = (page <= 1)? 0 : page - 1;
		return PageRequest.of( page , LIMIT, Sort.by(orderParam).ascending());
	}
	
}
