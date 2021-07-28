//package com.example.demo.feignclient;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.example.demo.dto.UserDetailsDTO;
//
///**
// * @author Rajkumar Banala 26-Feb-2021
// *
// */
//
//@FeignClient(name = "http://user-service/user/users")
//public interface UserClient {
//
//	@GetMapping("/loadUserByUsername")
//	public UserDetailsDTO loadUserByUsername(@RequestParam String username);
//}
