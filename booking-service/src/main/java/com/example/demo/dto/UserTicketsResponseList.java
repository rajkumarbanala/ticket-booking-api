package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Setter
@Getter
public class UserTicketsResponseList {
	
	private String id;
	
	private LocalDateTime departureTime;
	
	private String ticketNumber;
	
	private int numberOfSeats;
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	private LocalDateTime createdDate;
	
	private List<UserTicketDetailsResponseList> userDetails;
}
