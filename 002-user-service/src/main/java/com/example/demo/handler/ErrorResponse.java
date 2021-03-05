package com.example.demo.handler;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timeStamp;
	
	private Map<String, String> errors;
}
