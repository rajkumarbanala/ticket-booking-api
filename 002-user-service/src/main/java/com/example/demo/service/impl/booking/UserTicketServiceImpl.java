package com.example.demo.service.impl.booking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.order.TicketBookingRequestCreate;
import com.example.demo.dto.order.TicketBookingResponseCreate;
import com.example.demo.dto.order.UserTicketsListResponse;
import com.example.demo.exception.AppBaseException;
import com.example.demo.feignclient.UserTicketClient;
import com.example.demo.service.booking.UserTicketService;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

@Service
public class UserTicketServiceImpl implements UserTicketService {

	private static final Logger LOG = LoggerFactory.getLogger(UserTicketServiceImpl.class);

	@Autowired
	private UserTicketClient userTicketClient;

	@Transactional(readOnly = false)
	public TicketBookingResponseCreate bookTicket(String userId, TicketBookingRequestCreate ticketBookingRequestCreate) {
		LOG.debug("bookTicket()");
		
		return userTicketClient.bookTicket(userId, ticketBookingRequestCreate);
	}

	public List<UserTicketsListResponse> getUserTickets(String userId) {
		LOG.debug("getUserTickets");
		
		try {
			
			return userTicketClient.getUserTickets(userId);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new AppBaseException(e.getMessage());
		}
	}
}
