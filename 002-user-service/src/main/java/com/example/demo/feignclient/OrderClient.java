package com.example.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rajkumar Banala 19-Feb-2021
 *
 */

//@FeignClient(value = "order-service", url = "http://localhost:8082/order/orders")
@FeignClient(name = "http://order-service/order/orders")
public interface OrderClient {
	
	@GetMapping("/info")
	public String info();

}
