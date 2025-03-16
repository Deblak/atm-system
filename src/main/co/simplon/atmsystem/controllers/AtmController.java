package main.co.simplon.atmsystem.controllers;

import java.io.IOException;
import java.util.List;

import main.co.simplon.atmsystem.entities.AtmHardware;
import main.co.simplon.atmsystem.entities.Card;
import main.co.simplon.atmsystem.managers.AtmManager;
import main.co.simplon.atmsystem.services.AtmHardwareService;
import main.co.simplon.atmsystem.services.CardService;
import main.co.simplon.atmsystem.services.OperationService;
import main.co.simplon.atmsystem.utils.CsvReader;
import main.co.simplon.atmsystem.utils.FilePath;
import main.co.simplon.atmsystem.utils.StartReader;
import main.co.simplon.atmsystem.utils.UserInput;

/**
 * Run prompt application
 */
public class AtmController {
    private CsvReader csvReader;
    private CardService cardService;
    private OperationService operationService;
    private AtmManager atmManager;
    private AtmHardwareService atmHardwareService;

    public AtmController() {
	this.csvReader = new CsvReader();
	List<Card> cards = csvReader.csvFile(FilePath.CARDS);
	this.cardService = new CardService(csvReader);
	this.operationService = new OperationService(csvReader);
	this.atmHardwareService = new AtmHardwareService(new AtmHardware(0), csvReader);
	this.atmManager = new AtmManager(cardService, operationService, atmHardwareService, cards, null);
    }

    public void run() {
	try {
	    StartReader.readerText(null);
	    int cardNumber = UserInput.inputInt();

	    System.out.println("Saisir code PIN :");
	    int pin = UserInput.inputInt();

	    if (atmManager.pinRequest(cardNumber, pin)) {
		System.out.println("Code PIN valide.");
		menu();
	    } else {
		System.out.println("Code PIN invalide. Retrait carte.");
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void menu() {
	while (true) {
	    System.out.println("1: Consulter le solde. 2: Effectuer retrait. 3: exit");
	    int option = UserInput.inputInt();
	    if (option == 1) {
		System.out.println(atmManager.getBalance());
	    } else if (option == 2) {
		System.out.println("Entrer un montant multiple de 10.");
		int amount = UserInput.inputInt();
		System.out.println(atmManager.withdraw(amount));
	    } else if (option == 3) {
		System.out.println("A bientôt. N'oubliez pas votre carte.");
		UserInput.closeScanner();
		return;
	    }
	}
    }
}
