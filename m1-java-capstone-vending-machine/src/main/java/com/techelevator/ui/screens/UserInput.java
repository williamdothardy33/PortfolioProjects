package com.techelevator.ui.screens;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.techelevator.io.IOResources;
import com.techelevator.models.OptionCodes;
import com.techelevator.models.VendingItem;
import com.techelevator.models.exceptions.InvalidMoneyException;
import com.techelevator.models.exceptions.InvalidOptionException;

public class UserInput {
	private UserInput() {}
	
	public static String getMainMenuSelection() throws InvalidOptionException {
		Screen.getMainScreen();
		
		String input;
		Scanner in = IOResources.getInput();
		input = in.nextLine();
		
		switch(input) {
		case OptionCodes.DISPLAY:
			break;
		case OptionCodes.EXIT:
			break;
		case OptionCodes.PURCHASE:
			break;
		default:
			throw new InvalidOptionException("The option entered is invalid", input);
		}
		
		return input;
	}
	
	public static String getPurchaseMenuSelection() throws InvalidOptionException {
		Screen.getPurchaseScreen();
		
		String input;
		Scanner in = IOResources.getInput();
		input = in.nextLine();
		
		switch(input) {
		case OptionCodes.FEED_MONEY:
			break;
		case OptionCodes.FINISH_TRANSACTION:
			break;
		case OptionCodes.SELECT_PRODUCT:
			break;
		default:
			throw new InvalidOptionException("The option entered is invalid", input);
		}
		
		return input;
	}
	
	public static BigDecimal getMoney() throws InvalidMoneyException {
		Pattern moneyRegex = Pattern.compile("[^123456789]");
		
		System.out.println();
		System.out.println("Inserting money...");
		System.out.println("______________________________________________________________");
		Scanner in = IOResources.getInput();
		BigDecimal moneyInserted = new BigDecimal(0);
		String money = null;
		
		do {
			System.out.println();
			System.out.println();
			System.out.println("Enter whole dollar amounts (ex. 1, 2, 5, 10) and press enter");
			System.out.println("Press enter without entering anything to exit");
			System.out.println("----------------------------------------------------------------");
			System.out.println();
			System.out.println();
		
			money = in.nextLine();
			Matcher match = moneyRegex.matcher(money);
			if (match.find()) {
				String invalidMoney = match.group();
				throw new InvalidMoneyException("The money, " + invalidMoney + " is invalid!", invalidMoney);
			}
			if (!money.isBlank()) {
				moneyInserted = moneyInserted.add(new BigDecimal(money));
			}
			
			System.out.println("************************************");
			System.out.println();
			System.out.println("The current money inserted is: " + moneyInserted);
			System.out.println();
			System.out.println("************************************");

		}while(!money.isBlank());
		System.out.println();
		System.out.println("Finished inserting money");
		System.out.println("______________________________________________________________");
		System.out.println();
		
		return moneyInserted;
	}
	
	public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
	}
	
	public static String getProductSelection(List<VendingItem> contents, Map<String, LinkedList<VendingItem>> stock) {
		System.out.println();
		System.out.println("Selecting product");
		System.out.println("______________________________________________________________");
		System.out.println();
		Scanner in = IOResources.getInput();
		UserOutput.displayVendingItems(contents, stock);
		System.out.println("Please enter the slot id of the item you would like to buy");
		System.out.println();
		String slotID;
		slotID = in.nextLine();
		System.out.println();
		
		if (!stock.containsKey(slotID)) {
			System.out.println("The product code: " + slotID + " does not exist");
			slotID = null;
			return slotID;
		}else if (stock.get(slotID).size() == 0) {
			System.out.println("SOLD OUT");
			
			slotID = null;
			return slotID;
		}
		
		System.out.println();
		System.out.println("finished selecting product");
		System.out.println("______________________________________________________________");
		System.out.println();
		
		return slotID;
	}
}
