package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.feignclient.OrderClient;
import com.example.demo.service.UserService;

/**
 * @author Rajkumar Banala 19-Feb-2021
 *
 */

@RestController
@RequestMapping("/apip/info")
public class InfoController {

	private static final Logger LOG = LoggerFactory.getLogger(InfoController.class);

	@Autowired
	private OrderClient orderClient;

	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;

	@GetMapping()
	public String info() {
		LOG.debug("info()");

		String port = environment.getProperty("local.server.port");
		return "order-service-port: " + port;
	}

	@GetMapping("/getOrderServiceInfo")
	public String getOrderServiceInfo() {
		LOG.debug("getOrderServiceInfo()");

		return orderClient.info();
	}
}
