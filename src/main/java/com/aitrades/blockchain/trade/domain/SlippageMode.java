package com.aitrades.blockchain.trade.domain;

public enum SlippageMode {
	
	ZERO_POINT_ONE("ZERO_POINT_ONE",new Double(0.1),1),
	ZERO_POINT_THREE("ZERO_POINT_THREE",new Double(0.3),2),
	ZERO_POINT_FIVE("ZERO_POINT_FIVE",new Double(0.5),3),
	ONE("ONE",new Double(1.0),4),
	TWO("TWO",new Double(2.0),5),
	TWO_POINT_FIVE("TWO_POINT_FIVE",new Double(2.5),6),
	THREE_POINT("THREE_POINT",new Double(3.0),7),
	CUSTOME_POINT("CUSTOME_POINT",new Double(0.0),8);

	private final String name;
	private final Double value;
	private final Integer sortorder;
	
	private SlippageMode(String name, Double value, Integer sortorder) {
		this.name = name;
		this.value = value;
		this.sortorder = sortorder;
	}
	
	public String getName() {
		return name;
	}

	public Double getValue() {
		return value;
	}

	public Integer getSortorder() {
		return sortorder;
	}

	public static SlippageMode fromValue(Double value) {
		for (final SlippageMode gasMode : SlippageMode.values()) {
			if (gasMode.value.equals(value)) {
				return gasMode;
			}
		}
		return SlippageMode.fromValue(value);
	}

	public static SlippageMode fromName(String name) {

		for (final SlippageMode gasMode : SlippageMode.values()) {
			if (gasMode.name().equals(name)) {
				return gasMode;
			}
		}
		throw new IllegalArgumentException(name);

	}
}
