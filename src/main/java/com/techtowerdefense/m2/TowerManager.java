package com.techtowerdefense.m2;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.geometry.Point2D;

public enum TowerManager {

    INSTANCE;

    private GameDataController gdcsingleton = GameDataController.getInstance();

    public boolean notOnRoad(Point2D mousePos) {
        return mousePos.getY() < (3.7 * gdcsingleton.getappheight() / 6.0)
                || mousePos.getY() > (4.9 * gdcsingleton.getappheight() / 6.0);
    }

    public boolean placeTower(Point2D mousePos) {

        if (MoneyLogic.canBuy(gdcsingleton.getMoney(), TowerLogic.getChosenTower())) {

            if (TowerLogic.getChosenTower() != null && notOnRoad(mousePos)) {

                System.out.println(gdcsingleton.getMoney());
                gdcsingleton.setMoney(
                        gdcsingleton.getMoney() - TowerLogic.getChosenTower().getPrice());
                System.out.println(gdcsingleton.getMoney());

                FXGL.spawn(TowerLogic.getChosenTower().name(),
                        new SpawnData(mousePos.getX() - 30, mousePos.getY() - 30)
                );
            }
        }
        return false;
    }
}