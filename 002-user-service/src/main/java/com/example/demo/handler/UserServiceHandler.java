package com.example.demo.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.AppBaseException;

import feign.FeignException;

/**
 * @author Rajkumar Banala 12-Feb-2021
 *
 */

@RestControllerAdvice
public class UserServiceHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		LOG.debug("handleMethodArgumentNotValid()");
		
//		LOG.error(e.getMessage(), e);
		
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
		LOG.debug("constraintViolationException()");
		
//		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(HttpStatus.BAD_REQUEST.name());
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
		LOG.debug("appBaseException()");
		
//		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(e.getMessage());
		errorResponse.setErrorCode(400);
		
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.class)
	protected final Object feignException(FeignException e) {
		LOG.debug("feignException()");
		
//		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = ErrorResponse.parseErrorResponse(e.contentUTF8());
		
		if(errorResponse != null)
			return errorResponse;
		
		errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorResponse.setErrorCode(400);
		
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected final ErrorResponse unhandledException(Exception e) {
		LOG.debug("unhandledException()");
		
//		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorResponse.setErrorCode(400);
		
		return errorResponse;
	}
}
