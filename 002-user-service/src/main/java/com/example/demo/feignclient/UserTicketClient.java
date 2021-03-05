package com.example.demo.feignclient;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.order.TicketBookingRequestCreate;
import com.example.demo.dto.order.TicketBookingResponseCreate;
import com.example.demo.dto.order.UserTicketsResponseList;

/**
 * @author Rajkumar Banala 19-Feb-2021
 *
 */

@FeignClient(name = "http://booking-service/booking/api/userTickets", fallbackFactory = UserTicketClientFallbackFactory.class)
public interface UserTicketClient {
	
	@GetMapping("/info")
	public String info();
	
	@PostMapping("/{userId}")
	public TicketBookingResponseCreate bookTicket(@PathVariable String userId, @Valid @RequestBody TicketBookingRequestCreate ticketBookingRequestCreate);
	
	@GetMapping("/{userId}")
	public List<UserTicketsResponseList> getUserTickets(@PathVariable String userId);
}
