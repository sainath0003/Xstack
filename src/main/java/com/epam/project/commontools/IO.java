package com.epam.project.commontools;

import java.util.Scanner;

public class IO {
	private IO() {
	}

	private static Scanner scanner = new Scanner(System.in);

	public static String getInput() {

		return scanner.nextLine();

	}

	public static int getIntInput() {
		return Integer.parseInt(scanner.nextLine());
	}
	
	public static void close() {
		scanner.close();
	}
}
