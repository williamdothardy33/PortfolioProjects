package com.techelevator.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VendingInventory {
	private Map<String, LinkedList<VendingItem>> stock;
	private List<VendingItem> contents;
	
	public VendingInventory(String fileName, Loader<VendingItem> loader) {
		stock = loadStock(fileName, loader);
		contents = loader.load(fileName);
	}
	
	public Map<String, LinkedList<VendingItem>> getStock() {
		return stock;
	}

	public List<VendingItem> getContents() {
		return contents;
	}



	public Map<String, LinkedList<VendingItem>> loadStock(String fileName, Loader<VendingItem> vendingLoader) {
		Map<String, LinkedList<VendingItem>> stock = new HashMap<String, LinkedList<VendingItem>>();
		List<VendingItem> vendingItems = vendingLoader.load(fileName);
		
		for (VendingItem vendingItem: vendingItems) {
			
			LinkedList<VendingItem> slotItems = new LinkedList<VendingItem>();
			String slotID = vendingItem.getSlotID();
			
			for (int i = 1; i <= 5; i++) {
				slotItems.add(vendingItem);
			}
			
			stock.put(slotID, slotItems);
		}
		return stock;
	}
	
	public VendingItem takeItem(String slotID) {
		
		VendingItem item = stock.get(slotID).pollFirst();
		return item;
	}
	
	public VendingItem inspectItem(String slotID) {
		VendingItem item = stock.get(slotID).peek();
		return item;
	}

	public void displayStock() {
		for (VendingItem vendingItem: contents) {
			
			String SlotID = vendingItem.getSlotID();
			String name = vendingItem.getName();
			BigDecimal price = vendingItem.getPrice();
			int quantityRemaining = stock.get(SlotID).size();
			String available = Integer.toString(quantityRemaining);
			
			if (quantityRemaining == 0) {
				available = "SOLD OUT";
			}
			
			System.out.printf("%-5s%-20s%-7s%-4s\n", SlotID, name, price, available);
		}
	}	
}
