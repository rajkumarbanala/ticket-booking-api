package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

public interface UserDAO extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

}
