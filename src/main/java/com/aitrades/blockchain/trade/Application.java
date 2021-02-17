package com.aitrades.blockchain.trade;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.aitrades.blockchain.trade.client.Web3jServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication(scanBasePackages = { "com.aitrades.blockchain"})
@EnableAsync
public class Application {

	private String infuraRemoteNodeEndpointUrl ="https://mainnet.infura.io/v3/29f9565415d04f5786c43db50734d3bb";
	 
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(name="web3jClient")
    public Web3j web3J() {
        return Web3j.build(new HttpService(infuraRemoteNodeEndpointUrl));
    }
    
    @Bean
    public Web3jServiceClient accountInfoClient(@Qualifier("web3jClient") final Web3j web3j, final ObjectMapper objectMapper) {
		return new Web3jServiceClient(web3j, restTemplate(), objectMapper);
    }
    
    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
    
}
