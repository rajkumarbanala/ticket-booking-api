package com.example.demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.StationDAO;
import com.example.demo.dao.StationRouteDAO;
import com.example.demo.dao.TicketSettingsDAO;
import com.example.demo.dao.TrainDAO;
import com.example.demo.dao.TrainRouteDAO;
import com.example.demo.dao.TrainTimingsDAO;
import com.example.demo.dao.UserTicketDAO;
import com.example.demo.dao.UserTicketDetailsDAO;
import com.example.demo.dto.TicketBookingRequestCreate;
import com.example.demo.entity.TrainRoute;
import com.example.demo.exception.AppBaseException;

/**
 * @author Rajkumar Banala 05-Mar-2021
 *
 */

@ExtendWith(MockitoExtension.class)
class UserTicketServiceImplTests {

	@InjectMocks
	UserTicketServiceImpl userTicketServiceImpl;

	@Mock
	TrainDAO trainDAO;

	@Mock
	TrainRouteDAO trainRouteDAO;

	@Mock
	UserTicketDAO userTicketDAO;

	@Mock
	TrainTimingsDAO trainTimingsDAO;

	@Mock
	StationRouteDAO stationRouteDAO;

	@Mock
	StationDAO stationDAO;

	@Mock
	TicketSettingsDAO ticketSettingsDAO;

	@Mock
	UserTicketDetailsDAO userTicketDetailsDAO;

	@Test
	@DisplayName("Book Ticket Validations")
	void bookTicketValidations() {
		
		// mock data
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.empty());

		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate = new TicketBookingRequestCreate();
		
		// test
		AppBaseException ex1 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("123", ticketBookingRequestCreate);
		});
		
		// expect
		assertThat(ex1).isNotNull();
		assertEquals("TrainRoute not found", ex1.getMessage());
		
		// mock data
		TrainRoute trainRoute = new TrainRoute();
		trainRoute.setStationRouteId("1");
		
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(trainRoute));
		when(stationRouteDAO.findById(Mockito.any())).thenReturn(Optional.empty());
		
		// inputs
		
		// test
		AppBaseException ex2 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("123", ticketBookingRequestCreate);
		});
		
		// expect
		assertThat(ex2).isNotNull();
		assertEquals("StationRoute not found", ex2.getMessage());
		
	}
	@Test
	@DisplayName("Book Ticket Success")
	void bookTicketSuccess() {
		
		// mock data
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.empty());

		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate = new TicketBookingRequestCreate();

		// test
		AppBaseException ex2 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("123", ticketBookingRequestCreate);
		});
		
		// expect
		assertThat(ex2).isNotNull();
		assertEquals("TrainRoute not found", ex2.getMessage());
	}
}
