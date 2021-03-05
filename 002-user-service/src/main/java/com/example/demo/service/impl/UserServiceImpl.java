package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDAO;
import com.example.demo.dto.UserRequestCreateDTO;
import com.example.demo.dto.UserResponseCreateDTO;
import com.example.demo.dto.UserResponseListDTO;
import com.example.demo.entity.User;
import com.example.demo.security.dto.UserDetailsDTO;
import com.example.demo.service.UserService;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.debug("loadUserByUsername()");

		User user = userDAO.findByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException("Invalid username or password.");
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}
	
	public User getByUsername(String username) {
		LOG.debug("getByUsername()");

		return userDAO.findByUsername(username);
	}
	
	public UserDetailsDTO getUserByUsername(String username) {
		LOG.debug("getUserByUsername()");

		User user = userDAO.findByUsername(username);
		
		if(user == null)
			return null;
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
		BeanUtils.copyProperties(user, userDetailsDTO);
		return userDetailsDTO;
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		LOG.debug("getAuthority()");
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	
	@Transactional(readOnly = false)
	public UserResponseCreateDTO create(UserRequestCreateDTO dto) {
		LOG.debug("create()");
		
		//encode password
		dto.setPassword(bcryptEncoder.encode(dto.getPassword()));
		
		User user = new User();
		BeanUtils.copyProperties(dto, user);
		
		user = userDAO.save(user);
		
		if(user == null)
			throw new RuntimeException("unable to process details");
		
		UserResponseCreateDTO userResponseCreateDTO = new UserResponseCreateDTO();
		BeanUtils.copyProperties(userResponseCreateDTO, user);
		return userResponseCreateDTO;
	}
	
	public List<UserResponseListDTO> getUsers() {
		LOG.debug("getUsers()");
		
		List<User> userList = userDAO.findAll();
		
		if(userList.isEmpty())
			return new ArrayList<>();
		
		List<UserResponseListDTO> listDTO = new ArrayList<>();
		BeanUtils.copyProperties(userList, listDTO);
		return listDTO;
	}
}
