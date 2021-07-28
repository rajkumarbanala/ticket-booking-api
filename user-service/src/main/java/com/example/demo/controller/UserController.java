package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequestCreateDTO;
import com.example.demo.dto.UserResponseCreateDTO;
import com.example.demo.dto.UserResponseListDTO;
import com.example.demo.security.dto.UserDetailsDTO;
import com.example.demo.service.UserService;

/**
 * @author Rajkumar Banala 19-Feb-2021
 *
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@PostMapping()
	public UserResponseCreateDTO create(@RequestBody UserRequestCreateDTO dto) {
		LOG.debug("create()");
		
		return userService.create(dto);
	}
	
	@GetMapping()
	public List<UserResponseListDTO> getUsers() {
		LOG.debug("getUsers()");
		
		return userService.getUsers();
	}
	
	@GetMapping("/loadUserByUsername/{username}")
	public UserDetailsDTO getUserByUsername(@PathVariable String username) {
		LOG.debug("getUserByUsername()");
		
		return userService.getUserByUsername(username);
	}

}
