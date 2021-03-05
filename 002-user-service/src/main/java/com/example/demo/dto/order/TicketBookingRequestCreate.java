package com.example.demo.dto.order;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
public class TicketBookingRequestCreate {
	
	@NotNull
	private String trainRouteId;
	
	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime travelDate;
	
	@NotNull
	@Positive
	private Integer selectedSeats;
	
	private List<UserTicketDetailsRequestCreate> userDetails;
}
