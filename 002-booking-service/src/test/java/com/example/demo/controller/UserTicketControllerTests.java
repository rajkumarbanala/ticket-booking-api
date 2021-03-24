package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.dto.TicketBookingRequestCreate;
import com.example.demo.dto.TicketBookingResponseCreate;
import com.example.demo.dto.UserTicketsResponseList;
import com.example.demo.handler.ErrorResponse;
import com.example.demo.mapping.ApiMapping;
import com.example.demo.service.UserTicketService;
import com.example.demo.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author Rajkumar Banala 11-Mar-2021
 *
 */

@WebMvcTest(UserTicketController.class)
class UserTicketControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserTicketService userTicketService;
	
	static final String API = ApiMapping.UserTicketControllerMapping.API;
	
	@Test
	@DisplayName("Book Ticket Validations")
	void bookTicketValidations() {
		
		try {
			
			// mock data
			
			// inputs
			TicketBookingRequestCreate ticketBookingRequestCreate = new TicketBookingRequestCreate();
			String jsonString = JsonUtil.OBJECT_MAPPER.writeValueAsString(ticketBookingRequestCreate);
			
			// test
			URI uri = URI.create(API + "/" + 1);
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders
					.post(uri)
					.contentType(MediaType.APPLICATION_JSON.toString())
					.content(jsonString))
					.andReturn();
			
			// expect
			assertThat(result).isNotNull();
			status().isBadRequest().match(result);
			assertThat(result.getResponse()).isNotNull();
			assertThat(result.getResponse().getContentAsString()).isNotNull();
			
			ErrorResponse errorResponse = JsonUtil.OBJECT_MAPPER.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
			assertThat(errorResponse).isNotNull();
			MockMvcResultMatchers.jsonPath("$.errors.trainRouteId", Is.is("must not be null")).match(result);
			MockMvcResultMatchers.jsonPath("$.errors.travelDate", Is.is("must not be null")).match(result);
			MockMvcResultMatchers.jsonPath("$.errors.selectedSeats", Is.is("must not be null")).match(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Book Ticket Success")
	void bookTicketSuccess() {
		
		try {
			
			// given
			TicketBookingResponseCreate ticketBookingResponseCreateMock = new TicketBookingResponseCreate();
			TicketBookingRequestCreate ticketBookingRequestCreate = new TicketBookingRequestCreate();
			ticketBookingRequestCreate.setTrainRouteId("1");
			ticketBookingRequestCreate.setTravelDate(LocalDateTime.now());
			ticketBookingRequestCreate.setSelectedSeats(1);
			String jsonString = JsonUtil.OBJECT_MAPPER.writeValueAsString(ticketBookingRequestCreate);
			
			// when
			when(userTicketService.bookTicket(Mockito.any(), Mockito.any())).thenReturn(ticketBookingResponseCreateMock);
			
			// test
			URI uri = URI.create(API + "/" + 1);
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders
					.post(uri)
					.contentType(MediaType.APPLICATION_JSON.toString())
					.content(jsonString))
					.andExpect(status().isOk())
					.andReturn();

			assertThat(result).isNotNull();
			MockHttpServletResponse mockHttpServletResponse = result.getResponse();
			assertThat(mockHttpServletResponse).isNotNull();
			assertThat(mockHttpServletResponse.getContentAsString()).isNotNull();
			
			// expect
			TicketBookingResponseCreate response = JsonUtil.OBJECT_MAPPER.readValue(mockHttpServletResponse.getContentAsString(), TicketBookingResponseCreate.class);
			assertThat(response).isNotNull();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Get User Tickets Success")
	void getUserTicketsSuccess() {
		
		try {
			
			// given
			
			// when
			List<UserTicketsResponseList> list = new ArrayList<>();
			when(userTicketService.getUserTickets(Mockito.any())).thenReturn(list);
			
			// test
			URI uri = URI.create(API + "/" + 1);
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders
					.get(uri)
					.contentType(MediaType.APPLICATION_JSON.toString()))
					.andExpect(status().isOk())
					.andReturn();

			assertThat(result).isNotNull();
			MockHttpServletResponse mockHttpServletResponse = result.getResponse();
			assertThat(mockHttpServletResponse).isNotNull();
			assertThat(mockHttpServletResponse.getContentAsString()).isNotNull();
			
			// expect
			TypeReference<List<UserTicketsResponseList>> typeReferenceList = new TypeReference<List<UserTicketsResponseList>>() {
			};
			List<UserTicketsResponseList> response = JsonUtil.OBJECT_MAPPER.readValue(mockHttpServletResponse.getContentAsString(), typeReferenceList);
			assertThat(response).isNotNull();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
