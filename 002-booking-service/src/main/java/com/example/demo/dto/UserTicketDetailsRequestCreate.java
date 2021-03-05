package com.example.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Setter
@Getter
@ToString
public class UserTicketDetailsRequestCreate {
	
	private String firstName;
	
	private String lastName;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime dob;
	
	private String mobile;
	
	private String aadharNo;
	
	private String panNo;
}
