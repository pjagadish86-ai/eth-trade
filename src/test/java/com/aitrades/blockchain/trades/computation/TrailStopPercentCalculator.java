package com.aitrades.blockchain.trades.computation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TrailStopPercentCalculator {
	/*
	 * 
	 * public static void main(String[] args) {
	 * 
	 * BigDecimal inputTrailPercent = new
	 * BigDecimal(25).divide(BigDecimal.valueOf(100)).setScale(2,
	 * RoundingMode.HALF_UP); System.out.println(inputTrailPercent);
	 * 
	 * 
	 * BigDecimal atTheTimeSetTickerPrice = BigDecimal.valueOf(6).setScale(2,
	 * RoundingMode.HALF_UP);
	 * 
	 * System.out.println(atTheTimeSetTickerPrice);
	 * 
	 * // calcuate trailStopPrice
	 * 
	 * BigDecimal price =
	 * atTheTimeSetTickerPrice.subtract(atTheTimeSetTickerPrice.multiply(
	 * inputTrailPercent)); System.out.println(price);
	 * 
	 * //future increase in value 10;
	 * 
	 * BigDecimal currentMarketTickerPrice = BigDecimal.valueOf(10).setScale(2,
	 * RoundingMode.HALF_UP); // if price of your less than current market price
	 * if(price.compareTo(currentMarketTickerPrice) <= 0) { // //then calculate new
	 * trail percent value
	 * 
	 * price = currentMarketTickerPrice.subtract(currentMarketTickerPrice.multiply(
	 * inputTrailPercent));
	 * 
	 * System.out.println("<><><> save this new  in db "+price); }
	 * 
	 * if(price != null) { currentMarketTickerPrice =
	 * BigDecimal.valueOf(8).setScale(2, RoundingMode.HALF_UP);
	 * if(price.compareTo(currentMarketTickerPrice) > 0) {
	 * System.out.println("<><><> send order 1"+currentMarketTickerPrice); }
	 * 
	 * currentMarketTickerPrice = BigDecimal.valueOf(6).setScale(2,
	 * RoundingMode.HALF_UP); if(price.compareTo(currentMarketTickerPrice) > 0) {
	 * System.out.println(" sell the order "+currentMarketTickerPrice); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */}
