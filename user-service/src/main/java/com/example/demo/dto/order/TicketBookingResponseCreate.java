package com.example.demo.dto.order;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.util.JsonUtilLocalDateTimeDeSerializer;
import com.example.demo.util.JsonUtilLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	private LocalDateTime departureTime;
	
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	private LocalDateTime arrivalTime;
	
	private List<UserTicketDetailsResponseCreate> userDetails;

}
