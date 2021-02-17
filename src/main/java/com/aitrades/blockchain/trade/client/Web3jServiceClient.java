package com.aitrades.blockchain.trade.client;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.web3j.protocol.Web3j;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Web3jServiceClient {

	protected final Web3j web3j;
    protected final RestTemplate restTemplate;
    protected final ObjectMapper objectMapper;
    
	private static final Logger Logger = LoggerFactory.getLogger(Web3jServiceClient.class);

	public Web3jServiceClient(final Web3j web3j, final RestTemplate restTemplate, final ObjectMapper objectMapper) {
		this.web3j = web3j;
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	public Web3j getWeb3j() {
		return web3j;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}