package com.aitrades.blockchain.trade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
public class BuyOrderReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

	@Bean(name = "buyOrderMongoTemplate")
	public ReactiveMongoTemplate buyOrderMongoTemplate(final MongoClient client) {
		return new ReactiveMongoTemplate(client, getDatabaseName());
	}

	@Override
	protected String getDatabaseName() {
		return "snipeorder";
	}
	
}