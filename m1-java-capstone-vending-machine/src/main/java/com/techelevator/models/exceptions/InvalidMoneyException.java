package com.techelevator.models.exceptions;

import java.math.BigDecimal;

public class InvalidMoneyException extends Exception {
	private String moneyInserted;

	public InvalidMoneyException(String message, String moneyInserted){
        super(message);

        this.moneyInserted = moneyInserted;
        
    }

    public String getmoneyInserted(){
        return moneyInserted;
    }

    @Override
    public String getMessage(){
        String message = super.getMessage();

        return message 
            + "\nMoney Inserted: $" + moneyInserted.toString();
    }

}
