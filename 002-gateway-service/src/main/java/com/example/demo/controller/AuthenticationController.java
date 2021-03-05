//package com.example.demo.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.config.JwtTokenUtil;
//import com.example.demo.dto.ApiResponse;
//import com.example.demo.dto.AuthToken;
//import com.example.demo.dto.LoginUser;
//import com.example.demo.dto.UserDetailsDTO;
//import com.example.demo.service.UserService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/token")
//public class AuthenticationController {
//	
//	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);
//	
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    
//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;
//    
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ApiResponse<AuthToken> login(@RequestBody LoginUser loginUser) throws AuthenticationException {
//    	LOG.debug("login()");
//    	
//		Authentication auth  = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
//		
//		try {
//			LOG.debug("login().auth:"+new ObjectMapper().writeValueAsString(auth));
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		
//        final UserDetailsDTO user = userService.getUserByUsername(loginUser.getUsername());
//        
//        final String token = jwtTokenUtil.generateToken(user);
//        
//        return new ApiResponse<>(200, "success",new AuthToken(token, user.getUsername()));
//    }
//}
