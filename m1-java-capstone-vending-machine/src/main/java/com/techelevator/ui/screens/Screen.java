package com.techelevator.ui.screens;

public class Screen {
	private Screen() {}
	
	public static void getMainScreen() {
		System.out.println();
		System.out.println("Main Menu");
		System.out.println("_____________________________________________________________");
		System.out.println();
		String option1 = " Display Vending Machine Items ";
		String option2 = " Purchase ";
		String option3 = " Exit ";
		String select1 = "|select - 1";
		String select2 = "|select - 2";
		String select3 = "|select - 3";
		String padding = " ";
		String rightBorder = "|\n";
		String bottomBorder = "|__________\n";
		
		System.out.println();
		System.out.printf("%-35s%-10s\n", option1, select1);
		System.out.printf("%47s", bottomBorder);
		System.out.printf("%37s", rightBorder);
		System.out.printf("%10s%-25s%-10s\n", padding, option2, select2);
		System.out.printf("%47s", bottomBorder);
		System.out.printf("%37s", rightBorder);
		System.out.printf("%12s%-23s%-10s\n", padding, option3, select3);
		System.out.printf("%47s", bottomBorder);
		System.out.println();
		System.out.println();
		System.out.println("_____________________________________________________________");
		System.out.println();
	}
	
	public static void getPurchaseScreen() {
		System.out.println();
		System.out.println("Purchase Menu");
		System.out.println("_____________________________________________________________");
		System.out.println();
		String option1 = " Feed Money ";
		String option2 = " Select Product ";
		String option3 = " Finish Transaction ";
		String select1 = "|select - 1";
		String select2 = "|select - 2";
		String select3 = "|select - 3";
		String padding = " ";
		String rightBorder = "|\n";
		String bottomBorder = "|__________\n";
		
		System.out.println();
		System.out.printf("%-35s%-10s\n", option1, select1);
		System.out.printf("%47s", bottomBorder);
		System.out.printf("%37s", rightBorder);
		System.out.printf("%-35s%-10s\n", option2, select2);
		System.out.printf("%47s", bottomBorder);
		System.out.printf("%37s", rightBorder);
		System.out.printf("%-35s%-10s\n", option3, select3);
		System.out.printf("%47s", bottomBorder);
		System.out.println();
		System.out.println();
		System.out.println("_____________________________________________________________");
		System.out.println();
	}
}
