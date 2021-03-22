package com.aitrades.blockchain.trade.service.mq;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aitrades.blockchain.trade.domain.TransactionRequest;

@Service
public class RabbitMqSellOrderPublisher {

	@Value("${aitrades.order.submit.sell.rabbitmq.exchange}")
	String orderSubmitSellExchangeName;

	@Value("${aitrades.order.submit.sell.rabbitmq.routingkey}")
	private String orderSubmitSellRoutingkey;
	

	@Resource(name = "orderSubmitRabbitTemplate")
	public AmqpTemplate amqpTemplate;

	public void send(TransactionRequest transactionRequest) {
		amqpTemplate.convertAndSend(orderSubmitSellExchangeName, orderSubmitSellRoutingkey, transactionRequest);
		System.out.println("Send msg = " + transactionRequest);
	}
}
