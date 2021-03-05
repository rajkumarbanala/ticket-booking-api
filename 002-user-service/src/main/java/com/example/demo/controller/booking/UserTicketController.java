package com.example.demo.controller.booking;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.order.TicketBookingRequestCreate;
import com.example.demo.dto.order.TicketBookingResponseCreate;
import com.example.demo.dto.order.UserTicketsResponseList;
import com.example.demo.service.booking.UserTicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@RestController
@RequestMapping("/api/userTickets")
@Validated
public class UserTicketController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserTicketController.class);
	
	@Autowired
	private UserTicketService userTicketService;
	
	@PostMapping("/{userId}")
	public TicketBookingResponseCreate bookTicket(@PathVariable String userId, @Valid @RequestBody TicketBookingRequestCreate ticketBookingRequestCreate) {
		LOG.debug("bookTicket()");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			LOG.debug("bookTicket().auth:" + new ObjectMapper().writeValueAsString(auth));
			Object principal = auth.getPrincipal();
			LOG.debug("bookTicket().principal:" + new ObjectMapper().writeValueAsString(principal));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return userTicketService.bookTicket(userId, ticketBookingRequestCreate);
	}
	
	@GetMapping("/{userId}")
	public List<UserTicketsResponseList> getUserTickets(@PathVariable String userId) {
		LOG.debug("getUserTickets()");
		
		return userTicketService.getUserTickets(userId);
	}
}
