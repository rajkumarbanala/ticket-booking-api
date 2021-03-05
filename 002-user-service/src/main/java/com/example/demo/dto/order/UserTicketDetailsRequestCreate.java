package com.example.demo.dto.order;

import java.time.LocalDateTime;

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
	
	private LocalDateTime dob;
	
	private String mobile;
	
	private String aadharNo;
	
	private String panNo;
}
