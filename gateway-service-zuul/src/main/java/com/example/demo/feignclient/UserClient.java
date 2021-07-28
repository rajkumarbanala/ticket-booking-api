package com.example.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.UserDetailsDTO;

/**
 * @author Rajkumar Banala 26-Feb-2021
 *
 */
@FeignClient(value = "user-service", url = "http://localhost:8085/user/api/users")
//@FeignClient(name = "http://user-service/user/api/users")
public interface UserClient {

	@GetMapping("/loadUserByUsername/{username}")
	public UserDetailsDTO loadUserByUsername(@PathVariable String username);
}
