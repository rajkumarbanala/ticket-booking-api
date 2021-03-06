package com.example.demo.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserRequestCreateDTO {
	
	private String firstName;
	
	private String lastName;
	
	private String dob;
	
	private String mobile;
	
	private String email;
	
	private String username;
	
	private String password;
}
