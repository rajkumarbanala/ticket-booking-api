package com.example.demo.handler;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 16-Feb-2021
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
	
	private int errorCode;
	
	private String errorMessage;
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	private LocalDateTime timeStamp;
	
	private Map<String, String> errors;
	
	private static final Logger LOG = LoggerFactory.getLogger(ErrorResponse.class);
	
	private static final TypeReference<ErrorResponse> TYPE_REFERENCE_ERROR_RESPONSE = new TypeReference<ErrorResponse>() {
	};
	
	public static ErrorResponse parseErrorResponse(String errorResponseString) {
		LOG.debug("parseErrorResponse()");
		
		ErrorResponse errorResponse = null;
		
		try {
			
//			LOG.error("feignException().errorResponseString:" + errorResponseString);
			
			errorResponse = new ObjectMapper().readValue(errorResponseString, TYPE_REFERENCE_ERROR_RESPONSE);
			
			return errorResponse;			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return errorResponse;
		}
	}

}
