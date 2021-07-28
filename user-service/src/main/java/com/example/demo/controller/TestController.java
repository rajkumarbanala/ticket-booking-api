package com.example.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/apip/test")
public class TestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping()
    public String test(){
        return "Success";
    }
    
    @PostMapping("/testErrorResponse")
    public ErrorResponse testErrorResponse(@RequestBody ErrorResponse errorResponse) {
		LOG.debug("errorResponse()");
		
		TypeReference<Map<String, Object>> typeReferenceMap = new TypeReference<Map<String,Object>>() {
		};
		
		Map<String, Object> map = new ObjectMapper().convertValue(errorResponse, typeReferenceMap);
		LOG.debug("map:" + map);
		
		ErrorResponse errorResponse2 = new ErrorResponse();
		
		TypeReference<ErrorResponse> typeReferenceRrrorResponse = new TypeReference<ErrorResponse>() {
		};
		
		errorResponse2 = new ObjectMapper().convertValue(map, typeReferenceRrrorResponse);
		LOG.debug("errorResponse2:" + errorResponse2);
		
        return errorResponse;
    }
}
