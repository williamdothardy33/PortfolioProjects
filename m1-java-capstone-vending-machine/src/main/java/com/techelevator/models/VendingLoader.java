package com.techelevator.models;

import com.techelevator.models.Loader;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.techelevator.io.IOResources;

public class VendingLoader implements Loader<VendingItem> {
	private static final VendingLoader VENDINGLOADER = new VendingLoader();
	
	private VendingLoader() {}
	
	@Override
	public List<VendingItem> load(String fileName) {
		VendingItem vendingItem;
		List<VendingItem> vendingItems = new ArrayList<VendingItem>();
		
		try (Scanner in = IOResources.getFileInput(fileName)) {
			
			String line;
			while (in.hasNextLine()) {
				
				line = in.nextLine();
				String[] dataRow = line.split("\\|");
				String slotID = dataRow[0];
				String name = dataRow[1];
				BigDecimal price = new BigDecimal(dataRow[2]);
				String type = dataRow[3];
				
				switch(type) {
				case "Chip":
					vendingItem = new Chips(name, price, slotID);
					vendingItems.add(vendingItem);
					break;
					
				case "Candy":
					vendingItem = new Candy(name, price, slotID);
					vendingItems.add(vendingItem);
					break;
					
				case "Drink":
					vendingItem = new Drink(name, price, slotID);
					vendingItems.add(vendingItem);
					break;
					
				case "Gum":
					vendingItem = new Gum(name, price, slotID);
					vendingItems.add(vendingItem);
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return vendingItems;
	}
	
	public static Loader<VendingItem> getVendingLoader() {
		return VENDINGLOADER;
	}
}