package main.co.simplon.atmsystem.services;

import java.util.List;

import main.co.simplon.atmsystem.entities.Account;
import main.co.simplon.atmsystem.utils.CsvReader;
import main.co.simplon.atmsystem.utils.CsvWriter;
import main.co.simplon.atmsystem.utils.FilePath;

public class OperationService {
    private final CsvReader csvReader;

    public OperationService(CsvReader csvReader) {
	this.csvReader = csvReader;
    }

    public String withdraw(int cardNumber, double amount) {
	List<Account> accounts = csvReader.readAccounts(FilePath.ACCOUNTS);
	CsvWriter writer = new CsvWriter();

	for (Account account : accounts) {
	    if (account.getCardNumber() == cardNumber) {
		double balance = account.getBalance();
		if (amount % 10 != 0) {
		    return "Montant invalide. Vous n'avez pas été débité.";
		}
		if (amount > balance) {
		    return "Solde insuffisant";
		}

		double newBalance = balance - amount;
		account.setBalance(newBalance);

		writer.updateBalance(FilePath.ACCOUNTS, cardNumber, newBalance);

		return "Nouveau solde: " + newBalance + " €";
	    }
	}
	return "Echec communication.";
    }

    public String requestBalance(int cardNumber) {
	List<Account> accounts = csvReader.readAccounts(FilePath.ACCOUNTS);

	for (Account account : accounts) {
	    if (account.getCardNumber() == cardNumber) {
		return "Solde: " + account.getBalance() + " €";
	    }
	}
	return "Nous n'avons pas pu joindre votre compte.";
    }

}
