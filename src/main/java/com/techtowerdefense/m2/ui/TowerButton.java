package com.techtowerdefense.m2.ui;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class TowerButton extends Button {

    public TowerButton(String imagePath) {
        ImageView imageView = new ImageView();
        imageView.setImage(new Image(new File(imagePath).toURI().toString()));
        imageView.setPreserveRatio(true);

        if (imageView.getFitWidth() < imageView.getFitHeight()) {
            imageView.setFitHeight(110);
        } else {
            imageView.setFitWidth(110);
        }

        setGraphic(imageView);
        setPrefSize(80, 80);
        setStyle("-fx-background-color: transparent;");
    }

}
