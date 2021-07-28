package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TicketBookingRequestCreate;
import com.example.demo.dto.TicketBookingResponseCreate;
import com.example.demo.dto.UserTicketsResponseList;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

public interface UserTicketService {
	
	public TicketBookingResponseCreate bookTicket(String userId, TicketBookingRequestCreate ticketBookingRequestCreate);
	
	public List<UserTicketsResponseList> getUserTickets(String userId);

}
