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
class UserTicketServiceImplTest {

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
	@DisplayName("Book Ticket")
	void bookTicket() {
		
		// test
		Optional<TrainRoute> trainRouteOptional = Optional.empty();
		when(trainRouteDAO.findById(Mockito.any())).thenReturn(trainRouteOptional);

		TicketBookingRequestCreate ticketBookingRequestCreate = new TicketBookingRequestCreate();

		AppBaseException ex2 = assertThrows(AppBaseException.class, () -> {
			userTicketServiceImpl.bookTicket("123", ticketBookingRequestCreate);
		});
		
		// expect
		assertThat(ex2).isNotNull();
		assertEquals("Train Route not found", ex2.getMessage());
	}
}
