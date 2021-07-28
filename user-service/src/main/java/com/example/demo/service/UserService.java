package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserRequestCreateDTO;
import com.example.demo.dto.UserResponseCreateDTO;
import com.example.demo.dto.UserResponseListDTO;
import com.example.demo.entity.User;
import com.example.demo.security.dto.UserDetailsDTO;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */

public interface UserService {
	
	public UserResponseCreateDTO create(UserRequestCreateDTO dto);
	
	public User getByUsername(String username);
	
	public List<UserResponseListDTO> getUsers();
	
	public UserDetailsDTO getUserByUsername(String username);
}
