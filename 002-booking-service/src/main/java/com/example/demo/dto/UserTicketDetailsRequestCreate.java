package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserTicketDetailsRequestCreate {
	
	private String firstName;
	
	private String lastName;
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	private LocalDateTime dob;
	
	private String mobile;
	
	private String aadharNo;
	
	private String panNo;
}
