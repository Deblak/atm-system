package main.co.simplon.atmsystem.entities;

public enum AtmMenu {
    CONSULTER_SOLDE(1, "Consulter solde"), RETRAIT(2, "Retrait"), QUITTER(3, "Quitter");

    private final int option;
    private final String label;

    private AtmMenu(int option, String label) {
	this.option = option;
	this.label = label;
    }

}
