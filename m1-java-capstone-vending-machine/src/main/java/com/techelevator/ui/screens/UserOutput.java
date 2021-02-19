package com.techelevator.ui.screens;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.techelevator.io.IOResources;
import com.techelevator.models.VendingItem;

public class UserOutput {
	private UserOutput() {}
	
	public static void displayVendingItems(List<VendingItem> contents, Map<String, LinkedList<VendingItem>> stock) {
		System.out.println();
		System.out.println("Items for sale");
		System.out.println("_____________________________________________________________");
		for (VendingItem vendingItem: contents) {
			
			String SlotID = vendingItem.getSlotID();
			String name = vendingItem.getName();
			BigDecimal price = vendingItem.getPrice();
			int quantityRemaining = stock.get(SlotID).size();
			String available = Integer.toString(quantityRemaining);
			String padding = " ";
			
			if (quantityRemaining == 0) {
				available = "SOLD OUT";
			}
			
			System.out.printf("%-4s%-5s%-20s%-7s%-4s\n", padding, SlotID, name, price, available);
		}
		System.out.println();
		System.out.println("_____________________________________________________________");
		System.out.println();
	}
}