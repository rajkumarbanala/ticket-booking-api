package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TicketBookingRequestCreate;
import com.example.demo.dto.TicketBookingResponseCreate;
import com.example.demo.dto.UserTicketsResponseList;
import com.example.demo.mapping.ApiMapping;
import com.example.demo.service.UserTicketService;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@RestController
@RequestMapping(ApiMapping.UserTicketControllerMapping.API)
public class UserTicketController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserTicketController.class);
	
	@Autowired
	private UserTicketService userTicketService;
	
	@PostMapping("/{userId}")
	public TicketBookingResponseCreate bookTicket(@PathVariable String userId, @Valid @RequestBody TicketBookingRequestCreate ticketBookingRequestCreate) {
		LOG.debug("bookTicket()");
		
		return userTicketService.bookTicket(userId, ticketBookingRequestCreate);
	}
	
	@GetMapping("/{userId}")
	public List<UserTicketsResponseList> getUserTickets(@PathVariable String userId) {
		LOG.debug("getUserTickets()");
		
		return userTicketService.getUserTickets(userId);
	}
}
