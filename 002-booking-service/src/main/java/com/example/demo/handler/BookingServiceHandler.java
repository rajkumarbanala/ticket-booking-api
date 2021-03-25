package com.example.demo.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.AppBaseException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajkumar Banala 12-Feb-2021
 *
 */

@Slf4j
@RestControllerAdvice
public class BookingServiceHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		log.debug("handleMethodArgumentNotValid()");
		
		log.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(HttpStatus.BAD_REQUEST.name());
		errorResponse.setErrorCode(400);

		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		errorResponse.setErrors(errors);
		
		return errorResponse;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ErrorResponse constraintViolationException(ConstraintViolationException e) {
		log.debug("constraintViolationException()");
		
		log.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorResponse.setErrorCode(400);
		
		Map<String, String> errors = new HashMap<>();
		e.getConstraintViolations().forEach(cv -> {
			errors.put("message", cv.getMessage());
			errors.put("path", (cv.getPropertyPath()).toString());
		});
		
		errorResponse.setErrors(errors);

		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AppBaseException.class)
	protected final ErrorResponse appBaseException(AppBaseException e) {
		log.debug("appBaseException()");
		
		log.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(e.getMessage());
		errorResponse.setErrorCode(400);
		
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected final ErrorResponse unhandledException(Exception e) {
		log.debug("unhandledException()");
		
		log.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorResponse.setErrorCode(400);
		
		return errorResponse;
	}
}
