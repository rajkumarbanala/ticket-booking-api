package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserTicketDetails;

/**
 * @author Rajkumar Banala 02-Mar-2021
 *
 */

@Repository
public interface UserTicketDetailsDAO extends JpaRepository<UserTicketDetails, String> {
	
	public List<UserTicketDetails> findByUserTicketId(String userTicketId);
	
}
