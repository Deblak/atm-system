package main.co.simplon.atmsystem.controllers;

import java.io.IOException;
import java.util.List;

import main.co.simplon.atmsystem.entities.Card;
import main.co.simplon.atmsystem.managers.AtmManager;
import main.co.simplon.atmsystem.services.CardService;
import main.co.simplon.atmsystem.utils.CsvReader;
import main.co.simplon.atmsystem.utils.FilePath;
import main.co.simplon.atmsystem.utils.StartReader;

/**
 * Run prompt application
 */
public class AtmController {
    private CsvReader csvReader;
    private CardService cardService;
    private AtmManager atmManager;

    public AtmController() {
	this.csvReader = new CsvReader();
	List<Card> cards = csvReader.csvFile(FilePath.CARDS);
	this.cardService = new CardService(csvReader);
	this.atmManager = new AtmManager(cardService, cards);
    }

    public void run() {
	try {
	    StartReader.readerText(null);
	    if (atmManager.pinRequest()) {
		atmManager.menu();
	    } else {
		System.out.println("Annulation en cours.");
	    }
	    atmManager.exit();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
