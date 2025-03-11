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
     * @param updatedCard
     */
    public void updateCard(String csvPath, Card updatedCard) {
	try {
	    lines = Files.readAllLines(Paths.get(csvPath));
	    List<String> updatedLines = lines.stream().map(line -> {
		String[] parts = line.split(";");
		if (parts.length == 3 && parts[0].trim().equals(String.valueOf(updatedCard.getCardNumber()))) {
		    return updatedCard.getCardNumber() + ";" + updatedCard.getPin() + ";"
			    + updatedCard.isUnlockStatus();
		}
		return line;
	    }).collect(Collectors.toList());

	    Files.write(Paths.get(csvPath), updatedLines);
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
	    List<String> setLines = lines.stream().map(line -> {
		String[] parts = line.split(";");
		if (parts.length == 3 && parts[0].trim().equals(String.valueOf(cardNumber))) {
		    return parts[0] + ";" + parts[1] + ";" + unlockStatus;
		}
		return line;
	    }).collect(Collectors.toList());

	    Files.write(Paths.get(csvPath), setLines);
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
	    List<String> updatedLines = lines.stream().map(line -> {
		String[] parts = line.split(";");
		if (parts.length == 2 && parts[0].trim().equals(String.valueOf(cardNumber))) {
		    return cardNumber + ";" + balance;
		}
		return line;
	    }).collect(Collectors.toList());

	    Files.write(Paths.get(csvPath), updatedLines);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Echec mise à jour du solde.");
	}
    }

}
