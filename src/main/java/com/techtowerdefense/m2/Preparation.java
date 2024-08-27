package com.techtowerdefense.m2;

import javafx.scene.control.Alert;

public class Preparation {
    // check important data and start the game
    public Boolean isGameInitilization(com.techtowerdefense.m2.ui.scene.GameMainMenu gameMainMenu) {

        //create could not add alert
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Could Not Add");

        if (gameMainMenu.getOptionView().isDifficultynull()) {
            // invalid difficulty alert
            alert.setContentText("Improper data for certain difficulty");
            alert.showAndWait();
            return false;

        } else if (gameMainMenu.getOptionView().isplayernamestringnull()) {
            // invalid playername alert
            alert.setContentText("Improper data for certain playername");
            alert.showAndWait();
            return false;
        } else {

            // pass difficulty get from option
            gameMainMenu.getGDCsingleton().setHealth(gameMainMenu.
                    getOptionView().getDifficulty());
            gameMainMenu.getGDCsingleton().setMoney(gameMainMenu.
                    getOptionView().getDifficulty());
            gameMainMenu.getGDCsingleton().setPriceFact(gameMainMenu.
                    getOptionView().getDifficulty());

            // start the game,
            // we should configure all the stuffs before it kicks off
            return true;
        }
    }
}
