package main.co.simplon.atmsystem.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.co.simplon.atmsystem.entities.Card;

public class CsvReader {
    /**
     * Read all csv file
     *
     * @param csvPath
     * @return
     */
    public List<Card> csvFile(String csvPath) {
	List<Card> cards = new ArrayList<>();

	try {
	    cards = Files.lines(Paths.get(csvPath)).skip(1).map(line -> line.split(";")).map(parts -> {
		int cardNumber = Integer.parseInt(parts[0].trim());
		int pin = Integer.parseInt(parts[1].trim());
		boolean unlockStatus = Boolean.parseBoolean(parts[2].trim());
		return new Card(cardNumber, pin, unlockStatus);
	    }).collect(Collectors.toList());

	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Resource not found.");
	}
	return cards;
    }

    /**
     * Select card number and PIN
     *
     * @param data
     * @param cardNumber
     * @return
     */
    public Card getCardNumber(List<Card> cards, int cardNumber) {
	for (Card card : cards) {
	    if (card.getCardNumber() == cardNumber) {
		return card;
	    }
	}
	return null;
    }
}
