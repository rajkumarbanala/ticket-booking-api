package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserTicket;

/**
 * @author Rajkumar Banala 02-Mar-2021
 *
 */

@Repository
public interface UserTicketDAO extends JpaRepository<UserTicket, String> {
	
	@Query("select sum(numberOfSeats) from UserTicket where trainRouteId=:trainRouteId and date(departureTime)=date(:departureTime) and hour(departureTime)=hour(:departureTime) and minute(departureTime)=minute(:departureTime)")
	public Long countSeatsForTicketBooking(String trainRouteId, String departureTime);
	
	@Query("select count(*) from UserTicket where trainRouteId=:trainRouteId and date(departureTime)=date(:departureTime) and hour(departureTime)=hour(:departureTime) and minute(departureTime)=minute(:departureTime)")
	public Long countUsersForTicketBooking(String trainRouteId, String departureTime);
	
	public List<UserTicket> findByUserIdOrderByCreatedDateAsc(String userId);
}
