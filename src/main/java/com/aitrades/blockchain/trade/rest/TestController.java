package com.aitrades.blockchain.trade.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	@PostMapping("/createOrder")
	public String createOrder() {
		return "hello";
	}
	
	@PostMapping("/cancelOrder")
	public void cancelOrder() {
		
	}
	
	@PostMapping("/modifyOrder")
	public void modifyOrder() {
		
	}
	
}
