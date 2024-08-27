package com.techtowerdefense.m2.ui;

import com.almasb.fxgl.texture.Texture;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.data.TowerData;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;
import static com.almasb.fxgl.dsl.FXGL.texture;


public class TowerIcon extends VBox {

    private Texture texture;
    private TowerData data;
    private GameDataController gdcSingleton = GameDataController.getInstance();

    public TowerIcon(TowerData data) {
        this.data = data;

        var bg = new Circle(35, 35, 35);
        bg.setStroke(Color.WHITE);
        bg.setStrokeWidth(2.5);

        var text = getUIFactoryService().newText(data.cost()*gdcSingleton.getPriceFact() + "");
        text.setStroke(Color.BLACK);

        texture = texture(data.imageName());

        var stackPane = new StackPane(bg, texture);

        setSpacing(10);
        setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(stackPane, text);
    }

    public void bindToMoney(IntegerProperty property) {
        texture.opacityProperty().bind(
                Bindings.when(property.greaterThanOrEqualTo(data.cost()*gdcSingleton.getPriceFact()))
                        .then(1.0)
                        .otherwise(0.25)
        );
    }
}
