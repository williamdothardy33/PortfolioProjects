package com.techelevator.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOResources {
	private static Scanner input;
	private static Scanner fileInput;
	
	private IOResources() {}
	
	public static Scanner getInput() {
		if (input == null) {
			input = new Scanner(System.in);
		}
		
		return input;
	}
	
	public static Scanner getFileInput(String fileName) throws FileNotFoundException {
		File filePath = new File(fileName);
		fileInput = new Scanner(filePath);
		return fileInput;
	}
	
	public static void closeResources() {
		if (input != null) {
			input.close();
		}
	}
}
