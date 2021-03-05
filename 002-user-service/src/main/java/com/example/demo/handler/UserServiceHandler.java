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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		LOG.error(e.getMessage(), e);
		
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
		
		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		//errorResponse.setTimeStamp(LocalDateTime.now());
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
		
		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		//errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(e.getMessage());
		errorResponse.setErrorCode(400);
		
		return errorResponse;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FeignException.class)
	protected final Object feignException(FeignException e) {
		LOG.debug("feignException()");
		
		TypeReference<FeignErrorResponse> typeReferenceErrorResponse = new TypeReference<FeignErrorResponse>() {
		};
		
		FeignErrorResponse feignErrorResponse = null;
		
		String errorResponseString = e.contentUTF8();
		
		LOG.error("feignException().errorResponseString:" + errorResponseString);
		
		try {
			feignErrorResponse = new ObjectMapper().convertValue(errorResponseString, typeReferenceErrorResponse);
			LOG.debug("feignException().errorResponse1:" + feignErrorResponse);
		} catch (Exception e2) {
//			LOG.error(e2.getMessage(), e2);
			
			try {
				feignErrorResponse = new ObjectMapper().readValue(errorResponseString, typeReferenceErrorResponse);
				LOG.debug("feignException().errorResponse2:" + feignErrorResponse);
			} catch (JsonProcessingException e1) {
//				LOG.error(e1.getMessage(), e1);
				
				try {
					feignErrorResponse = new ObjectMapper().readValue(errorResponseString, FeignErrorResponse.class);
					LOG.debug("feignException().errorResponse3:" + feignErrorResponse);
				} catch (JsonProcessingException e3) {
//					LOG.error(e3.getMessage(), e3);
					
					
					try {
						
						feignErrorResponse = new ObjectMapper().convertValue(errorResponseString, FeignErrorResponse.class);
						LOG.debug("feignException().errorResponse4:" + feignErrorResponse);
					} catch (Exception e4) {
//						LOG.error(e3.getMessage(), e3);
					}
				}
			}
		}
		
		if(feignErrorResponse != null) {
			
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setTimeStamp(LocalDateTime.now());
			errorResponse.setErrorMessage(feignErrorResponse.getErrorMessage());
			errorResponse.setErrorCode(400);
			return errorResponse;
		}
		
		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimeStamp(LocalDateTime.now());
		feignErrorResponse.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		feignErrorResponse.setErrorCode(400);
		
		return feignErrorResponse;
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	protected final ErrorResponse unhandledException(Exception e) {
		LOG.debug("unhandledException()");
		
		LOG.error(e.getMessage(), e);
		
		ErrorResponse errorResponse = new ErrorResponse();
		//errorResponse.setTimeStamp(LocalDateTime.now());
		errorResponse.setErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
		errorResponse.setErrorCode(400);
		
		return errorResponse;
	}
}
