package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
public class UserTicketDetailsResponseCreate {
	
	private Long seatNo;
	
	private String firstName;
	
	private String lastName;
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	private LocalDateTime dob;
	
	private String mobile;
	
	private String aadharNo;
	
	private String panNo;
}
