package com.example.demo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDetailsDTO;
import com.example.demo.dto.UserRequestCreateDTO;
import com.example.demo.dto.UserResponseCreateDTO;
import com.example.demo.feignclient.UserClient;
import com.example.demo.feignclient.UserPublicClient;
import com.example.demo.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements /* UserDetailsService, */ UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserClient userClient;
	
	@Autowired
	UserPublicClient userPublicClient;
	
//	@Autowired
//	private BCryptPasswordEncoder bcryptEncoder;
	
//	public UserResponseCreateDTO create(UserRequestCreateDTO dto) {
//		LOG.debug("create()");
//		
//		//encode password
//		dto.setPassword(bcryptEncoder.encode(dto.getPassword()));
//		
//		return userPublicClient.create(dto);
//	}
	
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		LOG.debug("loadUserByUsername()");
//
//		UserDetailsDTO user = getUserByUsername(username);
//		
//		LOG.debug("loadUserByUsername().user:"+user);
//
//		if (user == null) {
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
//	}
//
//	private List<SimpleGrantedAuthority> getAuthority() {
//		LOG.debug("getAuthority()");
//		
//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//	}
	
	public UserDetailsDTO getUserByUsername(String username) {
		LOG.debug("getUserByUsername()");
		
		LOG.debug("getUserByUsername().username:" + username);
		
		return userClient.loadUserByUsername(username);
	}
}
