package main.co.simplon.atmsystem;

import main.co.simplon.atmsystem.controllers.AtmController;

public class Application {
    public static void main(String[] args) {
	AtmController atmController = new AtmController();
	atmController.run();
    }
}
