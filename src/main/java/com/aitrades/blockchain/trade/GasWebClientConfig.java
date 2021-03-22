package com.aitrades.blockchain.trade;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class GasWebClientConfig {
	
	private static final long WEBCLIENT_TIMEOUT= 20l;
	private static final String ETH_USD_PRICE_ORACLE ="https://api.cryptonator.com/api/ticker/eth-usd";
	
	@Bean(name = "gasWebClient")
	public WebClient getWebClient(@Qualifier("externalHttpClientCalls") HttpClient externalHttpClientCalls) {
		return WebClient.builder()
					    .baseUrl(ETH_USD_PRICE_ORACLE)
					    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
					    .clientConnector(new ReactorClientHttpConnector(externalHttpClientCalls))
					    .build();
	}

	@Bean(name = "externalHttpClientCalls")
	public HttpClient getHttpClient() {
		return HttpClient.create()
	    		  .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
	    		  .responseTimeout(Duration.ofMillis(5000))
	    		  .doOnConnected(conn -> 
	    		  						conn.addHandlerLast(new ReadTimeoutHandler(WEBCLIENT_TIMEOUT, TimeUnit.MILLISECONDS))
	    		  							.addHandlerLast(new WriteTimeoutHandler(WEBCLIENT_TIMEOUT, TimeUnit.MILLISECONDS)));
	}
}
