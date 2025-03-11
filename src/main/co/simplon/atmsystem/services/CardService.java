package main.co.simplon.atmsystem.services;

import java.util.List;

import main.co.simplon.atmsystem.entities.Card;
import main.co.simplon.atmsystem.utils.CsvReader;
import main.co.simplon.atmsystem.utils.CsvWriter;
import main.co.simplon.atmsystem.utils.FilePath;

public class CardService {

    private final CsvReader csvReader;
    private final List<Card> data;

    public CardService(CsvReader csvReader) {
	this.csvReader = csvReader;
	this.data = csvReader.csvFile(FilePath.CARDS);
    }

    private Card getCard(List<Card> cards, int cardNumber) {
	for (Card card : cards) {
	    if (card.getCardNumber() == cardNumber) {
		return card;
	    }
	}
	return null;
    }

    public boolean cardExists(List<Card> cards, int cardNumber) {
	Card card = getCard(cards, cardNumber);

	if (card != null) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * Validate PIN code
     *
     * @param card
     * @return
     */
    public boolean validate(List<Card> cards, int cardNumber, int inputPin) {
	Card card = getCard(cards, cardNumber);
	if (card != null) {
	    return card.getPin() == inputPin;
	}
	return false;
    }

    /**
     * Unlock only the first time when user connect
     *
     * @param card
     * @return
     */
    public boolean unlock(List<Card> cards, int cardNumber) {
	Card card = getCard(cards, cardNumber);
	CsvWriter writer = new CsvWriter();
	if (card != null && !card.isUnlockStatus()) {
	    writer.updateStatus(FilePath.CARDS, cardNumber, true);
	    return true;
	}
	return false;
    }
}