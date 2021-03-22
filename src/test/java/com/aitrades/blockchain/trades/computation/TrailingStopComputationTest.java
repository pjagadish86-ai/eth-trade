package com.aitrades.blockchain.trades.computation;

import java.math.BigDecimal;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SuppressWarnings("unused")
//so if you have 25% tralling stop, you'd use 100%-25% = 75%; buyPrice*75%= value stored in db and then 100%-25% = 75% ; currentprice*75% and if 
// current db stored value price is less than 
public class TrailingStopComputationTest {
	
	private BigDecimal oneHundredPercent = new BigDecimal(100).setScale(2,BigDecimal.ROUND_HALF_UP);

	private String currentTicker = "RWN";
	private BigDecimal currentTickerPriceInUSD = new BigDecimal(5).setScale(2,BigDecimal.ROUND_HALF_UP);
	
	private BigDecimal plannedTickerTraillingStop = new BigDecimal(25).setScale(2,BigDecimal.ROUND_HALF_UP);
	
	private static BigDecimal adjustedStopPrice = null;
	
	private BigDecimal currentTickerPriceInUSD_10 = new BigDecimal(10).setScale(2,BigDecimal.ROUND_HALF_UP);
	private BigDecimal currentTickerPriceInUSD_8 = new BigDecimal(8).setScale(2,BigDecimal.ROUND_HALF_UP);
	
	@Test
	@Order(1)
	public void testTraillingStopComputationStep1() {
		if(currentTickerPriceInUSD != null) {
			adjustedStopPrice = oneHundredPercent.subtract(plannedTickerTraillingStop)
					  .multiply(currentTickerPriceInUSD)
					  .divide(oneHundredPercent).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		System.out.println("UNIT TEST 1 :--> "+currentTicker +""+ " adjustStopPrice: "+adjustedStopPrice);
		
	}
	
	@Test
	@Order(2)
	public void testTraillingStopComputationStep2() {
		System.out.println("\n UNIT TEST 2 :--> "+currentTicker +""+ " adjustStopPrice: "+adjustedStopPrice);
		BigDecimal adjustedStopPrice_step2 = oneHundredPercent.subtract(plannedTickerTraillingStop)
				  							                  .multiply(currentTickerPriceInUSD_10)
				  							                  .divide(oneHundredPercent).setScale(2,BigDecimal.ROUND_HALF_UP);
		if(adjustedStopPrice.compareTo(adjustedStopPrice_step2) == 0) {// both equals
		
		}else if(adjustedStopPrice.compareTo(adjustedStopPrice_step2) == 1) {// first value is greater than second value
		
		}else if(adjustedStopPrice.compareTo(adjustedStopPrice_step2) == -1) {// second value is greater than first value
			adjustedStopPrice = adjustedStopPrice_step2;
		}
		
		System.out.println("UNIT TEST 2 :--> "+currentTicker +""+ " adjustStopPrice: "+adjustedStopPrice);
		
	}
	
	@Test
	@Order(3)
	public void testTraillingStopComputationStep3() {
		System.out.println("\n UNIT TEST 3 :--> "+currentTicker +""+ " adjustStopPrice: "+adjustedStopPrice);
		BigDecimal adjustedStopPrice_step3 = oneHundredPercent.subtract(plannedTickerTraillingStop)
                  .multiply(currentTickerPriceInUSD_8)
                  .divide(oneHundredPercent).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		if(adjustedStopPrice.compareTo(adjustedStopPrice_step3) == 0) {// both equals
			
		}else if((adjustedStopPrice.compareTo(currentTickerPriceInUSD_8) == 1 ) && (adjustedStopPrice.compareTo(adjustedStopPrice_step3) == 1)) {// first value is greater than second value
			
			System.out.println("please sell as adjustprice now is "+ adjustedStopPrice_step3);
			adjustedStopPrice = adjustedStopPrice_step3;
		
		}else if(adjustedStopPrice.compareTo(adjustedStopPrice_step3) == -1) {// second value is greater than first value
		
		}
		// if the adjusted price is more than current price adjusted 
		 //UNIT TEST 3 :--> RWN adjustStopPrice: 7.50  current price 8 
		System.out.println("UNIT TEST 3 :--> "+currentTicker +""+ " adjustStopPrice: "+adjustedStopPrice);
	}
	
	
}
