package com.techelevator.models;

import java.math.BigDecimal;

public class VendingItem {
	private String name;
	private BigDecimal price;
	private String slotID;
	
	public VendingItem(String name, BigDecimal price, String slotID) {
		super();
		this.name = name;
		this.price = price;
		this.slotID = slotID;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getSlotID() {
		return slotID;
	}
}
