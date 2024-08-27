package com.techtowerdefense.m2.ui;

import com.almasb.fxgl.core.util.EmptyRunnable;
import javafx.scene.Parent;
import javafx.scene.control.Button;


public class WaveIcon extends Parent {

    private Button startButton = new Button("Start");

    private Runnable waveStartAction = EmptyRunnable.INSTANCE;

    public WaveIcon() {

        startButton.setOnAction(e -> {
            waveStartAction.run();
        });

    }

    public void setMaxWave(int maxWave) {
    }

    public void scheduleWave(Runnable action) {
        this.waveStartAction = action;
    }
}
