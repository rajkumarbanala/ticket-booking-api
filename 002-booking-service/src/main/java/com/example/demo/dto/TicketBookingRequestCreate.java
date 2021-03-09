package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
public class TicketBookingRequestCreate {
	
	@NotNull
	private String trainRouteId;
	
	@NotNull
	@JsonSerialize(using = JsonUtilLocalDateTimeSerializer.class)
	@JsonDeserialize(using = JsonUtilLocalDateTimeDeSerializer.class)
	private LocalDateTime travelDate;
	
	@NotNull
	@Positive
	private Integer selectedSeats;
	
	private List<UserTicketDetailsRequestCreate> userDetails;
}
