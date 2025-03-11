package main.co.simplon.atmsystem.entities;

public class Card {
    private int cardNumber;
    int pin;
    boolean unlockStatus;

    public Card(int cardNumber, int pin, boolean unlockStatus) {
	this.cardNumber = cardNumber;
	this.pin = pin;
	this.unlockStatus = unlockStatus;
    }

    public boolean isUnlockStatus() {
	return unlockStatus;
    }

    public int getCardNumber() {
	return cardNumber;
    }

    public int getPin() {
	return pin;
    }

    @Override
    public String toString() {
	return "Card [cardNumber=" + cardNumber + ", pin=" + pin + ", unlockStatus=" + unlockStatus + "]";
    }

    public void setUnlockStatus(boolean unlockStatus) {
	this.unlockStatus = unlockStatus;
    }

}
