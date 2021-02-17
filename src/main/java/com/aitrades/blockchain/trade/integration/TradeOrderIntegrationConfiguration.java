package com.aitrades.blockchain.trade.integration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.context.IntegrationContextUtils;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.SplitterEndpointSpec;
import org.springframework.integration.mongodb.inbound.MongoDbMessageSource;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.splitter.DefaultMessageSplitter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.aitrades.blockchain.trade.domain.Order;

@Configuration
@ComponentScan("com.aitrades.blockchain.trade.integration")
@IntegrationComponentScan("com.aitrades.blockchain.trade.integration")
@EnableIntegration
public class TradeOrderIntegrationConfiguration {

    @SuppressWarnings("unused")
	@Autowired
    private MongoTransactionManager mongoTransactionManager;

    
	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata poller() {
		PollerMetadata poll = Pollers.fixedDelay(3, TimeUnit.SECONDS).get();
		poll.setTaskExecutor(executor());
	//	poll.setAdviceChain(transactionInterceptor());
		return poll;
	}

	@Bean
	public TaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setThreadNamePrefix("default_task_executor_thread");
		executor.initialize();
		return executor;
	}

	@Bean
	@Autowired
	public IntegrationFlow processProduct(MongoDatabaseFactory mongo) {
		return IntegrationFlows.from(mongoInboundSource(mongo), c -> c.poller(Pollers.fixedDelay(3, TimeUnit.SECONDS)))
				.handle("tradeOrderProcessGatewayEndpoint", "validateInput")
				.handle("tradeOrderProcessGatewayEndpoint", "tradeOrdercomputation")
				.handle("tradeOrderProcessGatewayEndpoint", "transformInput")
				.handle("tradeOrderProcessGatewayEndpoint", "web3jApprove")
				.handle("tradeOrderProcessGatewayEndpoint", "web3jTransaction")
				.channel(IntegrationContextUtils.NULL_CHANNEL_BEAN_NAME).get();
	}

	@Bean
	public TradeOrderProcessGatewayEndpoint tradeOrderProcessGatewayEndpoint() {
		return new TradeOrderProcessGatewayEndpoint();
	}

	@Bean
	@Autowired
	public MessageSource<Object> mongoInboundSource(MongoDatabaseFactory mongo) {
		MongoDbMessageSource messageSource = new MongoDbMessageSource(mongo, new LiteralExpression("{'side' : 'buy'}"));
		messageSource.setExpectSingleResult(true);
		messageSource.setEntityClass(Order.class);
		messageSource.setCollectionNameExpression(new LiteralExpression("order"));
		return messageSource;
	}
}
