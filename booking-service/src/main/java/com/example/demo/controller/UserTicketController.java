package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.example.demo.mapping.UserTicketControllerMapping;
import com.example.demo.service.UserTicketService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajkumar Banala 15-Feb-2021
 *
 */

@Slf4j
@RestController
@RequestMapping(UserTicketControllerMapping.API)
public class UserTicketController {
	
	@Autowired
	private UserTicketService userTicketService;
	
	@PostMapping("/{userId}")
	public TicketBookingResponseCreate bookTicket(@PathVariable String userId, @Valid @RequestBody TicketBookingRequestCreate ticketBookingRequestCreate) {
		log.debug("bookTicket()");
		
		return userTicketService.bookTicket(userId, ticketBookingRequestCreate);
	}
	
	@GetMapping("/{userId}")
	public List<UserTicketsResponseList> getUserTickets(@PathVariable String userId) {
		log.debug("getUserTickets()");
		
		return userTicketService.getUserTickets(userId);
	}
}
