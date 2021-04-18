package com.apirest.springboot.api.errores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({
		BadRequestException.class,
		org.springframework.web.HttpRequestMethodNotSupportedException.class,
		org.springframework.web.bind.MethodArgumentNotValidException.class
	})
	@ResponseBody
	public MansajeError badRequest(HttpServletRequest request, Exception exception) {
		return new MansajeError(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class	})
	@ResponseBody
	public MansajeError fatalErrorUnexpectatedException(HttpServletRequest request, Exception exception) {
		return new MansajeError(exception, request.getRequestURI());
	}
	
}
