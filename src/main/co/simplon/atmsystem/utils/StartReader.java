package main.co.simplon.atmsystem.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartReader {
    public static void readerText(String[] args) throws IOException {
	try {
	    String expected_value = "ATM System";
	    Path path = Paths.get("src/main/resources/banner.txt");
	    int i = 0;
	    while (i != 14) {
		String read = Files.readAllLines(path).get(i);
		i++;
		System.out.println(read);
	    }
	} finally {
	    System.out.println("Insérer carte.");

	}
    }
}
