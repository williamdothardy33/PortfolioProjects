package com.techelevator.models;

import java.math.BigDecimal;

public class Chips extends VendingItem {

	public Chips(String name, BigDecimal price, String slotID) {
		super(name, price, slotID);
	}

	@Override
	public String toString() {
		return "Crunch Crunch, Yum!";
	}
}
