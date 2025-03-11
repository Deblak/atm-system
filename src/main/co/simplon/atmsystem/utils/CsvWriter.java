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
    /**
     * Update card in the CSV file
     *
     * @param csvPath
     * @param updatedCard
     */
    public void updateCard(String csvPath, Card updatedCard) {
	List<String> lines;
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
     * Update staus lock/unlock in CSV file
     *
     * @param csvPath
     * @param setStatus
     */
    public void updateStatus(String csvPath, int cardNumber, boolean unlockStatus) {
	List<String> lines;
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
	    System.out.println("Error updating unlock status for card: " + cardNumber);
	}
    }

}
