package com.techtowerdefense.m2.ui.scene;

import com.almasb.fxgl.ui.FontType;
import javafx.beans.binding.Bindings;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactoryService;

public class MenuButton extends Parent {

    public MenuButton(String name, Runnable action) {
        var bg = new Polygon(
                0.0, 0.0,
                200.0, 0.0,
                200.0, 25.0,
                190.0, 40.0,
                0.0, 40.0
        );
        bg.setStrokeWidth(2.5);
        bg.strokeProperty().bind(
                Bindings.when(bg.hoverProperty()).then(Color.ALICEBLUE).otherwise(Color.BLACK)
        );

        bg.fillProperty().bind(
                Bindings.when(bg.pressedProperty()).
                        then(Color.YELLOW).otherwise(Color.color(0.1, 0.05, 0.0, 0.75))
        );

        var text = getUIFactoryService().newText(name, Color.WHITE, FontType.UI, 26.0);
        text.setTranslateX(15);
        text.setTranslateY(28);
        text.fillProperty().bind(
                Bindings.when(disableProperty())
                        .then(Color.GRAY)
                        .otherwise(
                                Bindings.when(pressedProperty()).
                                        then(Color.ALICEBLUE).otherwise(Color.WHITE)
                        )
        );

        setOnMouseClicked(e -> action.run());

        getChildren().addAll(bg, text);
    }
}