package com.techtowerdefense.m2.ui.scene;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.image.Image;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.Preparation;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.scene.Node;

import javafx.scene.layout.*;

import javafx.scene.paint.ImagePattern;
import org.jetbrains.annotations.NotNull;
import com.almasb.fxgl.animation.Interpolators;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import static com.almasb.fxgl.dsl.FXGL.*;

public class GameMainMenu extends FXGLMenu {

    private static final int SIZE = 150;

    private Animation<?> animation;

    private Pane contentBox = new Pane();

    public OptionView getOptionView() {
        return optionView;
    }


    public GameDataController getGDCsingleton() {
        return gdcsingleton;
    }

    private OptionView optionView = new OptionView();

    // GameDataController singleton
    private GameDataController gdcsingleton = GameDataController.getInstance();

    private Preparation preparation = new Preparation();

    public GameMainMenu(@NotNull MenuType type) {
        super(type);

        // Welcome screen initialized in the content
        var welcomescreen = new Nullview();



        // background and title
        Stop[] stops = new Stop[] { new Stop(0, Color.DARKSEAGREEN), new Stop(1, Color.LIGHTSKYBLUE)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        var bg = new Rectangle(getAppWidth(), getAppHeight(), Color.PALETURQUOISE);
        var title = texture("NewTitle.PNG");
        bg.setFill(lg1);
        title.setFitHeight(750);
        title.setFitWidth(550);
        title.setTranslateX(FXGL.getAppWidth() / 2.0 - 320);
        title.setTranslateY(50);

        var techTower = texture("TechTower.png");
        techTower.setScaleY(3.5);
        techTower.setScaleX(3.5);
        techTower.setTranslateX(1700);
        techTower.setTranslateY(450);

        // menu button arrangement and fire fuction
        var menuBox = new VBox(-15,
                new MenuButton("New Game", () -> {
                    if (preparation.isGameInitilization(this)) {
                        fireNewGame();
                    }
                }),
                new MenuButton("Settings", () -> setContent(optionView)),
                new MenuButton("Exit", () -> fireExit())
        );

        // set positions for menu and content
        menuBox.setTranslateX(SIZE + 100);
        menuBox.setTranslateY(getAppHeight() / 2.0 - SIZE + 120);
        contentBox.setTranslateX(getAppWidth() / 2.0 - SIZE);
        contentBox.setTranslateY(getAppHeight() / 2.0 - SIZE);
        menuBox.setScaleX(1.5);
        menuBox.setScaleY(1.5);

        // set arrangement for all nodes
        getContentRoot().getChildren().addAll(bg, title, menuBox, contentBox, techTower);
        getContentRoot().setScaleX(0);
        getContentRoot().setScaleY(0);


        // animation part
        animation = animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();

    }


    // render contentBox to show different view
    private void setContent(Node view) {
        contentBox.getChildren().setAll(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
    }

    @Override
    protected void onUpdate(double tpf) {
        animation.onUpdate(tpf);
    }
}
