package com.example.demo.service.booking;

import java.util.List;

import com.example.demo.dto.order.TicketBookingRequestCreate;
import com.example.demo.dto.order.TicketBookingResponseCreate;
import com.example.demo.dto.order.UserTicketsListResponse;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

public interface UserTicketService {
	
	public TicketBookingResponseCreate bookTicket(String userId, TicketBookingRequestCreate ticketBookingRequestCreate);
	
	public List<UserTicketsListResponse> getUserTickets(String userId);

}
