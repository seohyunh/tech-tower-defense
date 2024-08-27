package com.techtowerdefense.m2.ui;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.techtowerdefense.m2.SimpleGameApp;
import com.techtowerdefense.m2.data.TowerData;
import com.techtowerdefense.m2.data.Vars;
import javafx.scene.layout.HBox;

import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.getip;


public class TowerSelectionBox extends HBox {

    private Entity cell;

    public TowerSelectionBox(List<TowerData> towerData) {
        setSpacing(5);

        towerData.forEach(data -> {
            var icon = new TowerIcon(data);
            icon.bindToMoney(getip(Vars.MONEY));
            icon.setOnMouseClicked(e -> {
                if (cell != null) {
                    FXGL.<SimpleGameApp>getAppCast().onTowerSelected(cell, data);
                }
            });

            getChildren().add(icon);
        });
    }

    public void changeList(List<TowerData> towerData) {
        setSpacing(5);

        getChildren().clear();

        towerData.forEach(data -> {
            var icon = new TowerIcon(data);
            icon.bindToMoney(getip(Vars.MONEY));
            icon.setOnMouseClicked(e -> {
                if (cell != null) {
                    FXGL.<SimpleGameApp>getAppCast().onTowerSelected(cell, data);
                }
            });

            getChildren().add(icon);
        });
    }

    public void setCell(Entity cell) {
        this.cell = cell;
    }

    public Entity getCell() {
        return cell;
    }
}
