package main.co.simplon.atmsystem.managers;

import java.util.List;

import main.co.simplon.atmsystem.entities.Card;
import main.co.simplon.atmsystem.services.CardService;
import main.co.simplon.atmsystem.services.OperationService;
import main.co.simplon.atmsystem.utils.UserInput;

/**
 * connection and display menus
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
	    this.currentCard = cardNumber;
	    return true;
	} else {
	    System.out.println("Code PIN invalide.");
	    return false;
	}
    }

    public void menu() {
	System.out.println("1: Consulter le solde. 2: Effectuer retrait. 3: exit");
	int option = UserInput.inputInt();

	if (option == 1) {
	    if (currentCard != null) {
		System.out.println(operationService.requestBalance(currentCard));
		menu();
	    } else {
		System.out.println("Aucune carte active.");
	    }
	} else if (option == 3) {
	    System.out.println("A bientot. N'oubliez pas votre carte.");
	    UserInput.closeScanner();
	}
    }
}
