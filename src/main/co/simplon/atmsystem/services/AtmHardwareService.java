package main.co.simplon.atmsystem.services;

import main.co.simplon.atmsystem.entities.AtmHardware;
import main.co.simplon.atmsystem.utils.CsvReader;
import main.co.simplon.atmsystem.utils.CsvWriter;
import main.co.simplon.atmsystem.utils.FilePath;

public class AtmHardwareService {
    private AtmHardware atmHardware;
    private final CsvReader csvReader;

    public AtmHardwareService(AtmHardware atmHardware, CsvReader csvReader) {
	this.csvReader = csvReader;
	this.atmHardware = getCash(FilePath.ATM);
    }

    private AtmHardware getCash(String csvPath) {
	int cash = csvReader.readCash(FilePath.ATM);
	return new AtmHardware(cash);
    }

    public boolean checkCash(int amount) {
	boolean checkCash = atmHardware.getAvailableCash() >= amount;
	return checkCash;
    }

    public void cashBack(int amount) {
	CsvWriter writer = new CsvWriter();
	if (checkCash(amount)) {
	    atmHardware.setAvailableCash(atmHardware.getAvailableCash() - amount);
	    writer.updateCashBack(FilePath.ATM, atmHardware.getAvailableCash());
	} else {
	    System.out.println("Fonds du distributeur insuffisants.");
	}
    }

    public boolean checkJam() {
	boolean randomJam = Math.random() < 0.01;
	return randomJam;
    }
}
