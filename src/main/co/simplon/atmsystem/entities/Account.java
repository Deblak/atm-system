package main.co.simplon.atmsystem.entities;

public class Account {
    private int cardNumber;
    private double balance;

    public int getCardNumber() {
	return cardNumber;
    }

    public double getBalance() {
	return balance;
    }

    public void setBalance(double balance) {
	this.balance = balance;
    }

    public Account(int cardNumber, double balance) {
	this.cardNumber = cardNumber;
	this.balance = balance;
    }

}
