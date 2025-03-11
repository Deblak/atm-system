package main.co.simplon.atmsystem.managers;

import java.util.List;

import main.co.simplon.atmsystem.entities.Card;
import main.co.simplon.atmsystem.services.CardService;
import main.co.simplon.atmsystem.utils.UserInput;

/**
 * connection and display menus
 */
public class AtmManager {

    private CardService cardService;
    private List<Card> cards;

    public AtmManager(CardService cardService, List<Card> cards) {
	this.cardService = cardService;
	this.cards = cards;
    }

    public boolean pinRequest() {
	int cardNumber = UserInput.inputInt();

	if (!cardService.cardExists(cards, cardNumber)) {
	    System.out.println("Carte invalide.");
	    return false;
	}

	System.out.println("Saisir code PIN :");
	int codePin = UserInput.inputInt();

	if (cardService.validate(cards, cardNumber, codePin)) {
	    System.out.println("Code PIN valide.");
	    cardService.unlock(cards, cardNumber);
	    return true;
	} else {
	    System.out.println("Code PIN invalide.");
	    return false;
	}
    }

    public void menu() {
	System.out.println("Choisir option :");
    }

    public void exit() {
	System.out.println("A bientot. N'oubliez pas votre carte.");
	UserInput.closeScanner();
    }
}
