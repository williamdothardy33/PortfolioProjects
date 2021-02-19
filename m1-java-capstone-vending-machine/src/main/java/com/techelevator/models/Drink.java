package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends VendingItem {

	public Drink(String name, BigDecimal price, String slotID) {
		super(name, price, slotID);
	}

	@Override
	public String toString() {
		return "Glug Glug, Yum!";
	}
}
