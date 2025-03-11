package main.co.simplon.atmsystem.managers;

import java.util.List;

import main.co.simplon.atmsystem.entities.Card;
import main.co.simplon.atmsystem.services.CardService;
import main.co.simplon.atmsystem.services.OperationService;
import main.co.simplon.atmsystem.utils.UserInput;

/**
 * Manage services for menu
 */
public class AtmManager {

    private CardService cardService;
    private OperationService operationService;
    private List<Card> cards;
    private Integer currentCard = null;

    public AtmManager(CardService cardService, List<Card> cards, OperationService operationService) {
	this.cardService = cardService;
	this.cards = cards;
	this.operationService = operationService;
    }

    public boolean pinRequest(int cardNumber, int pin) {

	if (!cardService.cardExists(cards, cardNumber)) {
	    return false;
	}

	if (cardService.validate(cards, cardNumber, pin)) {
	    cardService.unlock(cards, cardNumber);
	    this.currentCard = cardNumber;
	    return true;
	} else {
	    return false;
	}
    }

    public String getBalance() {
	if (currentCard != null) {
	    return operationService.requestBalance(currentCard);
	}
	return "Erreur lecture solde.";
    }

    public String withdraw(int amount) {
	if (currentCard == null) {
	    return "Retrait impossible.";
	}
	return operationService.withdraw(currentCard, amount);
    }

    public void menu() {
	System.out.println("1: Consulter le solde. 2: Effectuer retrait. 3: exit");
	int option = UserInput.inputInt();

	if (option == 1) {
	    if (currentCard != null) {
		System.out.println(operationService.requestBalance(currentCard));
	    } else {
		System.out.println("Erreur lecture solde.");
	    }
	    menu();

	} else if (option == 2) {

	    if (currentCard != null) {
		System.out.println("Entrer valeur.");
		int amount = UserInput.inputInt();
		System.out.println(operationService.withdraw(currentCard, amount));
	    } else {
		System.out.println("Retrait impossible.");
	    }
	    menu();

	} else if (option == 3) {
	    System.out.println("A bientot. N'oubliez pas votre carte.");
	    UserInput.closeScanner();
	}
    }
}
