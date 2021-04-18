package com.apirest.springboot.api.errores;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private static final String DESCRIPTION = "Bad Request Exception (400)";
	
	public BadRequestException(String detail) {
		super(DESCRIPTION + ". "+detail);
	}

}
