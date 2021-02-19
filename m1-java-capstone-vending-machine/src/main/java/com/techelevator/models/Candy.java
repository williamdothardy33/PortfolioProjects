package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends VendingItem {

	public Candy(String name, BigDecimal price, String slotID) {
		super(name, price, slotID);
	}

	@Override
	public String toString() {
		return "Munch Munch, Yum!";
	}
	
	

}
