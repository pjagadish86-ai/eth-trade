package com.aitrades.blockchain.trade.service.mq;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aitrades.blockchain.trade.domain.TransactionRequest;

@Service
public class RabbitMqBuyOrderPublisher {

	@Value("${aitrades.order.submit.buy.rabbitmq.exchange}")
	private String orderSubmitBuyExchangeName;

	@Value("${aitrades.order.submit.buy.rabbitmq.routingkey}")
	private String orderSubmitBuyRoutingkey;
	
	@Resource(name = "orderSubmitRabbitTemplate")
	public AmqpTemplate amqpTemplate;

	public void send(TransactionRequest transactionRequest) {
		amqpTemplate.convertAndSend(orderSubmitBuyExchangeName, orderSubmitBuyRoutingkey, transactionRequest);
		System.out.println("Send msg = " + transactionRequest);
	}
}
