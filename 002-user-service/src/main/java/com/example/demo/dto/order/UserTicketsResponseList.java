package com.example.demo.dto.order;

import java.time.LocalDateTime;
import java.util.List;

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
public class UserTicketsResponseList {
	
	private String id;
	
	private LocalDateTime departureTime;
	
	private String ticketNumber;
	
	private int numberOfSeats;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime createdDate;
	
	private List<UserTicketDetailsResponseList> userDetails;
}
