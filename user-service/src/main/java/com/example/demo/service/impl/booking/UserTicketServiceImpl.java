package com.example.demo.service.impl.booking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.order.TicketBookingRequestCreate;
import com.example.demo.dto.order.TicketBookingResponseCreate;
import com.example.demo.dto.order.UserTicketsResponseList;
import com.example.demo.exception.AppBaseException;
import com.example.demo.feignclient.UserTicketClient;
import com.example.demo.handler.ErrorResponse;
import com.example.demo.service.booking.UserTicketService;

import feign.FeignException;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

@Service
public class UserTicketServiceImpl implements UserTicketService {

	private static final Logger LOG = LoggerFactory.getLogger(UserTicketServiceImpl.class);

	@Autowired
	private UserTicketClient userTicketClient;
	
	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

	@Transactional(readOnly = false)
	public TicketBookingResponseCreate bookTicket(String userId, TicketBookingRequestCreate ticketBookingRequestCreate) {
		LOG.debug("bookTicket()");
		
//		return userTicketClient.bookTicket(userId, ticketBookingRequestCreate);
		
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("bookTicket");
		
//		return circuitBreaker.run(() -> userTicketClient.bookTicket(userId, ticketBookingRequestCreate), e -> bookTicketFallback(userId, ticketBookingRequestCreate));
//		return circuitBreaker.run(() -> userTicketClient.bookTicket(userId, ticketBookingRequestCreate), e -> {
//			return null;
//		});
		
		return circuitBreaker.run(() -> userTicketClient.bookTicket(userId, ticketBookingRequestCreate), e -> {
			
			ErrorResponse errorResponse = fallback(e);
			
			if(errorResponse != null)
				throw new AppBaseException(errorResponse.getErrorMessage());
			
			throw new AppBaseException("Unable to process details");
		});
	}
	
	public ErrorResponse fallback(Throwable e) {
		
		if(e instanceof FeignException) {
			
			FeignException feignException = (FeignException) e;
			
			return ErrorResponse.parseErrorResponse(feignException.contentUTF8());
		}
		return null;
	}
	
	public TicketBookingResponseCreate bookTicketFallback(String userId, TicketBookingRequestCreate ticketBookingRequestCreate) {
		LOG.debug("bookTicketFallback");
		
		return null;
	}

	public List<UserTicketsResponseList> getUserTickets(String userId) {
		LOG.debug("getUserTickets");
		
//		return userTicketClient.getUserTickets(userId);
		
		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("userTickets");
//		return circuitBreaker.run(() -> userTicketClient.getUserTickets(userId), e -> getUserTicketsFallBack(userId));
//		return circuitBreaker.run(() -> userTicketClient.getUserTickets(userId), e -> {
//		e.printStackTrace();
//		return null;
//		});
		return circuitBreaker.run(() -> userTicketClient.getUserTickets(userId), e -> {
			
			ErrorResponse errorResponse = fallback(e);
			
			if(errorResponse != null)
				throw new AppBaseException(errorResponse.getErrorMessage());

			throw new AppBaseException("Unable to fetch details");
		});
	}
	
	public List<UserTicketsResponseList> getUserTicketsFallBack(String userId) {
		LOG.debug("getUserTicketsFallBack");
		
		return null;
	}
}
