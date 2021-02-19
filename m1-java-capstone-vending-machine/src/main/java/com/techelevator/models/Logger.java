package com.techelevator.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	private static final String FILE_EXTENSION = ".txt";

    private String directory;

    public Logger(String directory){
        this.directory = directory;
    }

    public void logMessage(String message){
        String fileName = "Log"; 
        String logFilePath = directory + "/" + fileName + FILE_EXTENSION;
        File logFile = new File(logFilePath);
        
        String date = LocalDate.now().format(DateTimeFormatter.ISO_DATE); //YYYY-MM-DD
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        String log = date + " " + currentTime + " " + message;

        try(FileOutputStream out = new FileOutputStream(logFile, true);
        	PrintStream print = new PrintStream(out);
        		) {
			print.println(log);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
    }
}
