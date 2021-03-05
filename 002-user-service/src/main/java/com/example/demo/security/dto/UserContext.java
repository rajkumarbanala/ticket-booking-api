package com.example.demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rajkumar Banala 05-Mar-2021
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserContext {
	
	private Long id;
	
	private String username;
}
