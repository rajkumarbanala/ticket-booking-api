package com.example.demo.dto;

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
public class UserResponseListDTO {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String dob;
	
	private String mobile;
	
	private String email;
	
	private String username;
}
