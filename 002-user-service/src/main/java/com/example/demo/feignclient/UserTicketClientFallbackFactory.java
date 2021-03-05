package com.example.demo.feignclient;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.example.demo.dto.order.TicketBookingRequestCreate;
import com.example.demo.dto.order.TicketBookingResponseCreate;
import com.example.demo.dto.order.UserTicketsResponseList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;

/**
 * @author Rajkumar Banala 04-Mar-2021
 *
 */

@Component
public class UserTicketClientFallbackFactory implements FallbackFactory<UserTicketClient> {

	private static final Logger LOG = LoggerFactory.getLogger(UserTicketClientFallbackFactory.class);

	@Override
	public UserTicketClient create(Throwable cause) {
		LOG.debug("create()");

		try {
			LOG.debug("create().cause:" + new ObjectMapper().writeValueAsString(cause));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage(), e);
		}

		String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";
		LOG.debug("create().httpStatus:" + httpStatus);

		return new UserTicketClient() {

			@Override
			public String info() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public TicketBookingResponseCreate bookTicket(String userId, @Valid TicketBookingRequestCreate ticketBookingRequestCreate) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<UserTicketsResponseList> getUserTickets(String userId) {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}
}
