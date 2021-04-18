package com.apirest.springboot.api.errores;

public class MansajeError {
	
	private String exception;
	private String message;
	private String path;

	public MansajeError(Exception exception, String path) {
		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		this.path = path;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	public String getException() {
		return exception;
	}


	@Override
	public String toString() {
		return "MansajeError [exception=" + exception + ", message=" + message + ", path=" + path + "]";
	}

}
