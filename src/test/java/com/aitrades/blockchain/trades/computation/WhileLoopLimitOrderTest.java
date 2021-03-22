package com.aitrades.blockchain.trades.computation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class WhileLoopLimitOrderTest {

	private static final String VS_CURRENCIES_USD = "&vs_currencies=USD";
	private static final String BASE_URI = "https://api.coingecko.com/api/v3/simple/token_price/ethereum?contract_addresses=";
	List<TradeBook> tradeBooks = new ArrayList<>();
	RestTemplate restTemplate = new RestTemplate();
	Executor executor = Executors.newWorkStealingPool();
	
	@Before
	public void init() {
		
		TradeBook upiTradeBook = new TradeBook();
		upiTradeBook.setFromContractAddress("ETH");
		upiTradeBook.setToContractAddress("0x70d2b7c19352bb76e4409858ff5746e500f2b67c");
		upiTradeBook.setBuyLimit(new Double(0.006));
		upiTradeBook.setBuySide("BUY");
		upiTradeBook.setOrderLimitType("LIMIT");
		upiTradeBook.setEnsName("UPI");
		
		TradeBook baoTradeBook = new TradeBook();
		baoTradeBook.setFromContractAddress("ETH");
		baoTradeBook.setToContractAddress("0x374cb8c27130e2c9e04f44303f3c8351b9de61c1");
		baoTradeBook.setBuyLimit(new Double(0.0014));
		baoTradeBook.setBuySide("BUY");
		baoTradeBook.setOrderLimitType("LIMIT");
		baoTradeBook.setEnsName("BAO");
		
		TradeBook utuTradeBook = new TradeBook();
		utuTradeBook.setFromContractAddress("ETH");
		utuTradeBook.setToContractAddress("0xa58a4f5c4bb043d2cc1e170613b74e767c94189b");
		utuTradeBook.setBuyLimit(new Double(0.16));
		utuTradeBook.setBuySide("BUY");
		utuTradeBook.setOrderLimitType("LIMIT");
		utuTradeBook.setEnsName("UTU");
		
		TradeBook paidNetworkTradeBook = new TradeBook();
		paidNetworkTradeBook.setFromContractAddress("ETH");
		paidNetworkTradeBook.setToContractAddress("0x8c8687fc965593dfb2f0b4eaefd55e9d8df348df");
		paidNetworkTradeBook.setBuyLimit(new Double(4.25));
		paidNetworkTradeBook.setBuySide("BUY");
		paidNetworkTradeBook.setOrderLimitType("LIMIT");
		paidNetworkTradeBook.setEnsName("PAID");
		
		TradeBook sakeTradeBook = new TradeBook();
		sakeTradeBook.setFromContractAddress("ETH");
		sakeTradeBook.setToContractAddress("0x066798d9ef0833ccc719076dab77199ecbd178b0");
		sakeTradeBook.setBuyLimit(new Double(0.11));
		sakeTradeBook.setBuySide("BUY");
		sakeTradeBook.setOrderLimitType("LIMIT");
		sakeTradeBook.setEnsName("SAKE");
		
		tradeBooks.add(upiTradeBook);
		tradeBooks.add(baoTradeBook);
		tradeBooks.add(utuTradeBook);
		tradeBooks.add(paidNetworkTradeBook);
		tradeBooks.add(sakeTradeBook);
		
	}
	
	@Test
	public void whileLoopTestWithThread1() throws Exception {
		whileLoopTestWithThread(tradeBooks);
	}
	
	
	public void whileLoopTestWithThread(List<TradeBook> tradeBooks) throws Exception {
		CompletableFuture.runAsync(()-> {
				try {
					for(TradeBook tradeBook: tradeBooks) {
						whileLoopTest(tradeBook);
					}
				} catch (Exception e) {
					
				}
			}, executor)
		.join();
	}

	
	public void whileLoopTest(TradeBook tradeBook) throws Exception {
		Double price = getTokenPriceInUSD(tradeBook);
		System.out.println(price);
		boolean limitPriceSatisfied = isLimitPriceSatisfied(tradeBook.getBuyLimit(), price);
		do{
			if(!limitPriceSatisfied) {
				isLimitPriceSatisfied(tradeBook.getBuyLimit(), price);
			}
		}while(limitPriceSatisfied); 
		
	}

	private boolean isLimitPriceSatisfied(Double bidPrice, Double marketPrice) {
		return Double.compare(bidPrice, marketPrice) >= 0;
	}

	@SuppressWarnings("unchecked")
	private Double getTokenPriceInUSD(TradeBook tradeBook) throws Exception {
		StringBuffer buffer = new StringBuffer();
		buffer.append(BASE_URI);
		buffer.append(tradeBook.getToContractAddress());
		buffer.append(VS_CURRENCIES_USD);
		String uri = buffer.toString();
		System.out.println("fetching price for token->>> "+ tradeBook.getEnsName());
		String result = (String) restTemplate.getForObject(uri, String.class);
		Map<String, Object> results = new ObjectMapper().readValue(result, HashMap.class);
		Map<String, Double> x = (Map<String, Double>) results.get(tradeBook.getToContractAddress());
		return x.get("usd");
	}
}
