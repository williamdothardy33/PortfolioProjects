package com.techelevator.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import com.sun.tools.javac.util.List;
import com.techelevator.io.IOResources;
import com.techelevator.models.Logger;
import com.techelevator.models.OptionCodes;
import com.techelevator.models.VendingInventory;
import com.techelevator.models.VendingItem;
import com.techelevator.models.VendingLoader;
import com.techelevator.models.exceptions.CardDeclinedException;
import com.techelevator.models.exceptions.InvalidMoneyException;
import com.techelevator.models.exceptions.InvalidOptionException;
import com.techelevator.ui.screens.UserInput;
import com.techelevator.ui.screens.UserOutput;

public class VendingMachine 
{
	VendingInventory vendingInventory = new VendingInventory("vendingmachine.csv", VendingLoader.getVendingLoader());
	BigDecimal balance = new BigDecimal(0);
	Logger logger = new Logger("../../../../../");

	
    public void run()
    {
        while(true)
        {
        	String choice = "";
        	
        	try {
        		choice = UserInput.getMainMenuSelection();
        	}catch(InvalidOptionException e) {
        		System.out.println(e.getMessage());
        	}
            
          
            if(choice.equals(OptionCodes.DISPLAY))
            {
                UserOutput.displayVendingItems(vendingInventory.getContents(), vendingInventory.getStock());
            }
            else if(choice.equals(OptionCodes.PURCHASE))
            {
            	try {
            		vendor();
            	}catch(CardDeclinedException e) {
            		System.out.println(e.getMessage());
            	}
                
            }
            else if(choice.equals(OptionCodes.EXIT))
            {   
            	IOResources.closeResources();
                break;
            }
        }
    }
    
    public void vendor() throws CardDeclinedException {
    	while(true) {
    		System.out.println();
    		System.out.println("Your current balance is: " + balance);
    		System.out.println();
    		String choice = "";
    		
    		try {
    			choice = UserInput.getPurchaseMenuSelection();
    		}catch(InvalidOptionException e) {
    			System.out.println(e.getMessage());
    		}
    		BigDecimal moneyInserted = new BigDecimal(0);
    		
    		if(choice.equals(OptionCodes.FEED_MONEY)) {
    			try {
    				moneyInserted = UserInput.getMoney();
    			}catch(InvalidMoneyException e) {
    				System.out.println(e.getMessage());
    			}
    			
    			BigDecimal prevBalance = balance;
    			balance = balance.add(moneyInserted);
    			logger.logMessage("FEED MONEY: $" + prevBalance + " $" + balance);		
    		}else if(choice.equals(OptionCodes.SELECT_PRODUCT)) {
    			BigDecimal prevBalance = balance;
    			String slotID = UserInput.getProductSelection(vendingInventory.getContents(), vendingInventory.getStock());
    			if (slotID == null) {
    				continue;
    			}
    			VendingItem item = vendingInventory.inspectItem(slotID);
    			BigDecimal change = balance.subtract(item.getPrice());
    			
    			if (change.compareTo(BigDecimal.ZERO) < 0) {
    				throw new CardDeclinedException("You need more money sir", balance, item.getPrice());
    			}else {
    				balance = change;
    				item = vendingInventory.takeItem(slotID);
    				logger.logMessage(item.getName() + " " + item.getSlotID() + " $" + prevBalance + " $" + balance);
    				vend(item);
    			}
    		}else if(choice.equals(OptionCodes.FINISH_TRANSACTION)) {
    			getChange();
    			break;
    		}
    	}
    }
    
    
    public void vend(VendingItem item) {
    	System.out.println();
    	System.out.println("Dispensing item...");
    	System.out.println("__________________________________________");
    	System.out.println("You bought: " + item.getName());
    	System.out.println();
    	System.out.println("This vending product cost: " + item.getPrice());
    	System.out.println();
    	System.out.println("Your remaing balance is: " + balance);
    	System.out.println();
    	System.out.println(item);
    	System.out.println();
    	System.out.println("Finished dispensing vending product");
    	System.out.println("__________________________________________");
    }
    
    public void getChange() {
    	BigDecimal prevBalance = balance;
    	int pennyBalance = balance.setScale(2).multiply(new BigDecimal(100)).intValue();
    	balance = new BigDecimal(pennyBalance);
    	
    	int quarter = 25;
    	int dime = 10;
    	int nickel = 5;
    	int quarterQty = 0;
    	int dimeQty = 0;
    	int nickelQty = 0;
    	
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	
    	list.add(quarter);
    	list.add(dime);
    	list.add(nickel);
    
    	for (int i = 0; i < list.size(); i++) {
    		int quotient = pennyBalance / list.get(i);
    		if (quotient > 0) {
    			if (i == 0) {
    				
    				int changeSubtract = list.get(i)*quotient;
    				pennyBalance = pennyBalance - changeSubtract;
    				BigDecimal changeGiven = new BigDecimal(changeSubtract);
        			quarterQty = quotient;
        			balance = balance.subtract(changeGiven);
    			}
    			
    			if (i == 1) {
    				int changeSubtract = list.get(i)*quotient;
    				pennyBalance = pennyBalance - changeSubtract;
    				BigDecimal changeGiven = new BigDecimal(changeSubtract);
        			dimeQty = quotient;
        			balance = balance.subtract(changeGiven);
    			}
    			
    			if (i == 2) {
    				int changeSubtract = list.get(i)*quotient;
    				pennyBalance = pennyBalance - changeSubtract;
    				BigDecimal changeGiven = new BigDecimal(changeSubtract);
        			nickelQty = quotient;
        			balance = balance.subtract(changeGiven);
    			}
    		}
    		
    	}
    	logger.logMessage("GIVE CHANGE: $" + prevBalance + " $" + balance);
    	System.out.println("Your change is: Quarters - " + quarterQty + "| Dimes - " + dimeQty + "| Nickels - " + nickelQty);
    }
}
