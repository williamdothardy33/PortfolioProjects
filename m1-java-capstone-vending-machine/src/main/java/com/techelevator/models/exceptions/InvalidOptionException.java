package com.techelevator.models.exceptions;

public class InvalidOptionException extends Exception {

	private String option;

	public InvalidOptionException(String message, String option){
        super(message);

        this.option = option;
        
    }

    public String getOption(){
        return option;
    }

    @Override
    public String getMessage(){
        String message = super.getMessage();

        return message 
            + "\nOption entered: " + option;
    }

}
