package com.techelevator.models.exceptions;

import java.math.BigDecimal;

public class CardDeclinedException extends Exception {
	private BigDecimal balance;
	private BigDecimal cost;
	public CardDeclinedException(String message, BigDecimal balance, BigDecimal cost){
        super(message);

        this.balance = balance;
        this.cost = cost;
    }

    public BigDecimal getCost(){
        return this.cost;
    }

    public BigDecimal getbalance(){
        return this.balance;
    }

    @Override
    public String getMessage(){
        String message = super.getMessage();

        return message 
            + "\nCost: $" + cost.toString()
            + "\nBalance: $" + balance.toString();
    }

}
