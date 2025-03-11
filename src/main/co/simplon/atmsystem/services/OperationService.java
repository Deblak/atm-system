package main.co.simplon.atmsystem.services;

import java.util.List;

import main.co.simplon.atmsystem.entities.Account;
import main.co.simplon.atmsystem.utils.CsvReader;
import main.co.simplon.atmsystem.utils.FilePath;

public class OperationService {
    private final CsvReader csvReader;

    public OperationService(CsvReader csvReader) {
	this.csvReader = csvReader;
    }

    public String withdraw() {
	return null;
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
