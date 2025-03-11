package main.co.simplon.atmsystem.utils;

import java.util.Scanner;

/**
 * Handling input(s) with Scanner class Methods: inputString(), inputInt() and
 * closeScanner
 */
public class UserInput {
    static Scanner scanner = new Scanner(System.in);

    /**
     *
     * @return scanner.nextLine()
     */
    public static String inputString() {
	return scanner.nextLine();
    }

    /**
     *
     * @return scanner.nextInt()
     */
    public static int inputInt() {
	while (!scanner.hasNextInt()) {
	    System.out.println("Veuillez entrer un nombre valide.");
	    scanner.nextLine();
	}
	return scanner.nextInt();
    }

    /**
     *
     * @return scanner.close()
     */
    public static void closeScanner() {
	if (scanner != null) {
	    scanner.close();
	}
    }
}
