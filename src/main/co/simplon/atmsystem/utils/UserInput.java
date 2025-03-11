package main.co.simplon.atmsystem.utils;

import java.util.Scanner;

/**
 * Handling input(s) with Scanner class Methods: inputInt() and closeScanner
 */
public class UserInput {
    static Scanner scanner = new Scanner(System.in);

    /**
     *
     * @return scanner.nextInt()
     */
    public static int inputInt() {
	while (true) {
	    if (scanner.hasNextInt()) {
		return scanner.nextInt();
	    } else {
		System.out.println("Veuillez entrer un nombre valide.");
		scanner.next();
	    }
	}
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
