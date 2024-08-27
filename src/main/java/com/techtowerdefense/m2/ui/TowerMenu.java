package com.techtowerdefense.m2.ui;


import com.techtowerdefense.m2.TowerLogic;
import com.techtowerdefense.m2.TowerStats;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class TowerMenu extends StackPane {

    private final int width;
    private final int height;

    public TowerMenu(int width, int height) {
        this.width = width;
        this.height = height;

        createMenuUI();
    }

    public void createMenuUI() {


        var regBuzzTowerButton =
                new TowerButton("./src/main/resources/assets/textures/regBuzzTower.png");
        var ninjaBuzzTowerButton =
                new TowerButton("./src/main/resources/assets/textures/ninjaBuzzTower.png");
        var cannonTowerButton =
                new TowerButton("./src/main/resources/assets/textures/cannonTower.png");

        StackPane regBuzzSP = new StackPane();
        StackPane ninjaBuzzSP = new StackPane();
        StackPane cannonSP = new StackPane();



        Label regBuzzPrice = new Label("Price: " + TowerStats.REGBUZZTOWER.getPrice());
        regBuzzPrice.setStyle("-fx-text-fill: white; -fx-font-size: 13px;");
        Label ninjaBuzzPrice = new Label("Price: " + TowerStats.NINJABUZZTOWER.getPrice());
        ninjaBuzzPrice.setStyle("-fx-text-fill: white; -fx-font-size: 13px;");
        Label cannonPrice = new Label("Price: " + TowerStats.CANNONTOWER.getPrice());
        cannonPrice.setStyle("-fx-text-fill: white; -fx-font-size: 13px;");


        StackPane.setAlignment(regBuzzPrice, Pos.TOP_CENTER);
        StackPane.setAlignment(ninjaBuzzPrice, Pos.TOP_CENTER);
        StackPane.setAlignment(cannonPrice, Pos.TOP_CENTER);

        regBuzzSP.getChildren().addAll(regBuzzTowerButton, regBuzzPrice);
        ninjaBuzzSP.getChildren().addAll(ninjaBuzzTowerButton, ninjaBuzzPrice);
        cannonSP.getChildren().addAll(cannonTowerButton, cannonPrice);


//        regBuzzTowerButton.setOnAction((ActionEvent event) -> {
//            regBuzzTowerButton.setStyle("-fx-border: 3px solid; -fx-border-color: black; ");
//            ninjaBuzzTowerButton.setStyle("-fx-background-color: transparent; ");
//            cannonTowerButton.setStyle("-fx-background-color: transparent; ");
//
//            TowerLogic.setChosenTower(TowerStats.REGBUZZTOWER);
//        });

//        ninjaBuzzTowerButton.setOnAction((ActionEvent event) -> {
//            ninjaBuzzTowerButton.setStyle("-fx-border: 3px solid; -fx-border-color: black; ");
//            regBuzzTowerButton.setStyle("-fx-background-color: transparent; ");
//            cannonTowerButton.setStyle("-fx-background-color: transparent; ");
//
//            TowerLogic.setChosenTower(TowerStats.NINJABUZZTOWER);
//        });
//
//        cannonTowerButton.setOnAction((ActionEvent event) -> {
//            cannonTowerButton.setStyle("-fx-border: 3px solid; -fx-border-color: black; ");
//            ninjaBuzzTowerButton.setStyle("-fx-background-color: transparent; ");
//            regBuzzTowerButton.setStyle("-fx-background-color: transparent; ");
//
//            TowerLogic.setChosenTower(TowerStats.CANNONTOWER);
//        });

        VBox towerMenu = new VBox(3, regBuzzSP, ninjaBuzzSP, cannonSP);
        this.getChildren().addAll(towerMenu);
    }

}


