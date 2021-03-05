package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rajkumar Banala 03-Mar-2021
 *
 */

@Setter
@Getter
@ToString
public class TicketBookingResponseCreate {
	
	private String trainNumber;
	
	private String trainName;
	
	private String trainCode;
	
	private int journey;
	
	private String fromStation;
	
	private String toStation;
	
	private Long distance;
	
	private String ticketNumber;
	
	private int selectedSeats;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime departureTime;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime arrivalTime;
	
	private List<UserTicketDetailsResponseCreate> userDetails;

}
