package com.techtowerdefense.m2.ui.scene;

import javafx.scene.Parent;

import static com.almasb.fxgl.dsl.FXGL.texture;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class Nullview extends Parent {

    public Nullview() {
        var title = texture("welcome.png");
        getChildren().add(title);

    }


}
