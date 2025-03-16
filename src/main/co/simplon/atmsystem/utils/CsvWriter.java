package main.co.simplon.atmsystem.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import main.co.simplon.atmsystem.entities.Card;

/**
 * Replace Csv data
 */
public class CsvWriter {

    private List<String> lines;

    /**
     * Update card in the CSV file
     *
     * @param csvPath
     * @param updateCard
     */
    public void updateCard(String csvPath, Card updateCard) {
	try {
	    lines = Files.readAllLines(Paths.get(csvPath));
	    List<String> updateLines = lines.stream().map(line -> {
		String[] parts = line.split(";");
		if (parts.length == 3 && parts[0].trim().equals(String.valueOf(updateCard.getCardNumber()))) {
		    return updateCard.getCardNumber() + ";" + updateCard.getPin() + ";" + updateCard.isUnlockStatus();
		}
		return line;
	    }).collect(Collectors.toList());

	    Files.write(Paths.get(csvPath), updateLines);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Error updating file: " + csvPath);
	}
    }

    /**
     * Update status lock to unlock in CSV file
     *
     * @param csvPath
     * @param setStatus
     */
    public void updateStatus(String csvPath, int cardNumber, boolean unlockStatus) {
	try {
	    lines = Files.readAllLines(Paths.get(csvPath));
	    List<String> updateLines = lines.stream().map(line -> {
		String[] parts = line.split(";");
		if (parts.length == 3 && parts[0].trim().equals(String.valueOf(cardNumber))) {
		    return parts[0] + ";" + parts[1] + ";" + unlockStatus;
		}
		return line;
	    }).collect(Collectors.toList());

	    Files.write(Paths.get(csvPath), updateLines);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Erreur, carte lock");
	}
    }

    /**
     * Update balance in CSV file
     *
     * @param csvPath
     * @param balance
     */
    public void updateBalance(String csvPath, int cardNumber, double balance) {
	try {
	    lines = Files.readAllLines(Paths.get(csvPath));
	    List<String> updateLines = lines.stream().map(line -> {
		String[] parts = line.split(";");
		if (parts.length == 2 && parts[0].trim().equals(String.valueOf(cardNumber))) {
		    return cardNumber + ";" + balance;
		}
		return line;
	    }).collect(Collectors.toList());

	    Files.write(Paths.get(csvPath), updateLines);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Echec mise à jour du solde.");
	}
    }

    /**
     * Update ATM cash in CSV file
     *
     * @param csvPath
     * @param cash
     */
    public void updateCashBack(String csvPath, int cash) {
	try {
	    String setCash = new String(Files.readAllBytes(Paths.get(csvPath))).trim();
	    Files.write(Paths.get(csvPath), (String.valueOf(cash)).getBytes());

	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Echec mise à jour cash back.");
	}
    }

}
