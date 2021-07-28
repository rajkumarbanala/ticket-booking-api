package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDetailsDTO;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/apip/test")
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    public String test(){
    	LOG.debug("test()");
    	
        return "Success";
    }
    
	@Autowired
	UserService userService;
	
	@GetMapping("/loadUserByUsername/{username}")
	public UserDetailsDTO loadUserByUsername(@PathVariable String username) {
		LOG.debug("loadUserByUsername()");
		
		return userService.getUserByUsername(username);
	}

}
