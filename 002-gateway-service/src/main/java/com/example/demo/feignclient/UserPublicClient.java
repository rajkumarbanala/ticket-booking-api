//package com.example.demo.feignclient;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.example.demo.dto.UserRequestCreateDTO;
//import com.example.demo.dto.UserResponseCreateDTO;
//
///**
// * @author Rajkumar Banala 26-Feb-2021
// *
// */
//
//@FeignClient(name = "http://user-service/user/signup")
//public interface UserPublicClient {
//
//	@PostMapping()
//	public UserResponseCreateDTO create(@RequestBody UserRequestCreateDTO dto);
//}
