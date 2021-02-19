package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends VendingItem{

	public Gum(String name, BigDecimal price, String slotID) {
		super(name, price, slotID);
	}

	@Override
	public String toString() {
		return "Chew Chew, Yum!";
	}
}
