package main.co.simplon.atmsystem.entities;

public class AtmHardware {
    private int availableCash;

    public int getAvailableCash() {
	return availableCash;
    }

    public void setAvailableCash(int availableCash) {
	this.availableCash = availableCash;
    }

    public AtmHardware(int availableCash) {
	this.availableCash = availableCash;
    }

}
