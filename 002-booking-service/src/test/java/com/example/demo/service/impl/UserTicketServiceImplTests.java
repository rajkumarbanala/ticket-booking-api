package com.example.demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import com.example.demo.entity.Station;
import com.example.demo.entity.StationRoute;
import com.example.demo.entity.Train;
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
		
		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate1 = new TicketBookingRequestCreate();
		
		// mock data
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.empty());
		
		// test
		AppBaseException ex1 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("1", ticketBookingRequestCreate1);
		});
		
		// expect
		assertThat(ex1).isNotNull();
		assertEquals("TrainRoute not found", ex1.getMessage());
		
		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate2 = new TicketBookingRequestCreate();
		
		// mock data
		TrainRoute trainRoute1 = new TrainRoute();
		trainRoute1.setStationRouteId("1");
		
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(trainRoute1));
		when(stationRouteDAO.findById(Mockito.any())).thenReturn(Optional.empty());
		
		// test
		AppBaseException ex2 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("1", ticketBookingRequestCreate2);
		});
		
		// expect
		assertThat(ex2).isNotNull();
		assertEquals("StationRoute not found", ex2.getMessage());
		
		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate3 = new TicketBookingRequestCreate();
		
		// mock data
		TrainRoute trainRoute2 = new TrainRoute();
		trainRoute2.setStationRouteId("1");
		
		StationRoute stationRoute1 = new StationRoute();
		
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(trainRoute2));
		when(stationRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(stationRoute1));
		when(stationDAO.findById(Mockito.any())).thenReturn(Optional.empty());
		
		// test
		AppBaseException ex3 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("1", ticketBookingRequestCreate3);
		});
		
		// expect
		assertThat(ex3).isNotNull();
		assertEquals("fromStation not found", ex3.getMessage());

		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate4 = new TicketBookingRequestCreate();
		
		// mock data
		TrainRoute trainRoute3 = new TrainRoute();
		trainRoute3.setStationRouteId("1");
		
		StationRoute stationRoute2 = new StationRoute();
		stationRoute2.setFromStationId("1");
		stationRoute2.setToStationId("2");
		
		Station fromStation = new Station();
		
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(trainRoute3));
		when(stationRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(stationRoute2));
		when(stationDAO.findById(stationRoute2.getFromStationId())).thenReturn(Optional.of(fromStation));
		when(stationDAO.findById(stationRoute2.getToStationId())).thenReturn(Optional.empty());
		
		// test
		AppBaseException ex4 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("1", ticketBookingRequestCreate4);
		});
		
		// expect
		assertThat(ex4).isNotNull();
		assertEquals("toStation not found", ex4.getMessage());
	
		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate5 = new TicketBookingRequestCreate();
		
		// mock data
		TrainRoute trainRoute4 = new TrainRoute();
		trainRoute4.setStationRouteId("1");
		
		StationRoute stationRoute3 = new StationRoute();
		stationRoute3.setFromStationId("1");
		stationRoute3.setToStationId("2");
		
		Station fromStation1 = new Station();
		Station toStation1 = new Station();
		
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(trainRoute4));
		when(stationRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(stationRoute3));
		when(stationDAO.findById(stationRoute3.getFromStationId())).thenReturn(Optional.of(fromStation1));
		when(stationDAO.findById(stationRoute3.getToStationId())).thenReturn(Optional.of(toStation1));
		when(trainDAO.findById(Mockito.any())).thenReturn(Optional.empty());
		
		// test
		AppBaseException ex5 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("1", ticketBookingRequestCreate5);
		});
		
		// expect
		assertThat(ex5).isNotNull();
		assertEquals("Train not found", ex5.getMessage());

		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate6 = new TicketBookingRequestCreate();
		ticketBookingRequestCreate6.setTravelDate(LocalDateTime.now());
		
		// mock data
		TrainRoute trainRoute5 = new TrainRoute();
		trainRoute5.setStationRouteId("1");
		
		StationRoute stationRoute4 = new StationRoute();
		stationRoute4.setFromStationId("1");
		stationRoute4.setToStationId("2");
		
		Station fromStation2 = new Station();
		Station toStation2 = new Station();
		Train train = new Train();
		
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(trainRoute5));
		when(stationRouteDAO.findById(Mockito.any())).thenReturn(Optional.of(stationRoute4));
		when(stationDAO.findById(stationRoute4.getFromStationId())).thenReturn(Optional.of(fromStation2));
		when(stationDAO.findById(stationRoute4.getToStationId())).thenReturn(Optional.of(toStation2));
		when(trainDAO.findById(Mockito.any())).thenReturn(Optional.of(train));
		
		// test
		AppBaseException ex6 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("1", ticketBookingRequestCreate6);
		});
		
		// expect
		assertThat(ex6).isNotNull();
		assertEquals("All tickets are booked", ex6.getMessage());
		
	}
	@Test
	@DisplayName("Book Ticket Success")
	void bookTicketSuccess() {
		
		// inputs
		TicketBookingRequestCreate ticketBookingRequestCreate = new TicketBookingRequestCreate();
		
		// mock data
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(Optional.empty());

		// test
		AppBaseException ex2 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("123", ticketBookingRequestCreate);
		});
		
		// expect
		assertThat(ex2).isNotNull();
		assertEquals("TrainRoute not found", ex2.getMessage());
	}
}
