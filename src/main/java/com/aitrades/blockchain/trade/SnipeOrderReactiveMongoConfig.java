package com.aitrades.blockchain.trade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
public class SnipeOrderReactiveMongoConfig extends AbstractReactiveMongoConfiguration {

	@Bean(name = "snipeOrderMongoTemplate")
	public ReactiveMongoTemplate snipeOrderMongoTemplate(final MongoClient client) {
		return new ReactiveMongoTemplate(client, getDatabaseName());
	}

	@Override
	protected String getDatabaseName() {
		return "snipeorder";
	}
	
}