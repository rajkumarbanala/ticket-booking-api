//package com.example.demo.service.impl;
//
//import static org.hamcrest.CoreMatchers.any;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.example.demo.dao.UserTicketDAO;
//import com.example.demo.dto.TicketBookingRequestCreate;
//import com.example.demo.dto.TicketBookingResponseCreate;
//import com.example.demo.entity.UserTicket;
//
///**
// * @author Rajkumar Banala 05-Mar-2021
// *
// */
//
//@ExtendWith(MockitoExtension.class)
//public class UserTicketServiceImplTest {
//	
//	@Mock
//	UserTicketDAO userTicketDAO;
//	
//	@InjectMocks
//	UserTicketServiceImpl userTicketServiceImpl;
//	
//	@Test
//	@DisplayName("Book Ticket")
//	public void bookTicket() {
//		//context
//		
//		when(userTicketDAO.save(any(UserTicket.class))).thenAnswer( i -> {
//			UserTicket userTicket = i.getArgument(0);
//			userTicket.setId("1");
//			return userTicket;
//		});
//		
//		TicketBookingRequestCreate ticketBookingRequestCreate = new TicketBookingRequestCreate();
//		
//		//event
//		TicketBookingResponseCreate result = userTicketServiceImpl.bookTicket("123", ticketBookingRequestCreate);
//		
//		//outcome
//	}
//	
//}
