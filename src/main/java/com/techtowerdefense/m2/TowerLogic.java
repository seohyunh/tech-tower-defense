package com.techtowerdefense.m2;

import com.almasb.fxgl.entity.component.Component;

public class TowerLogic extends Component {

    private static TowerStats chosenTower;


    public static void setChosenTower(TowerStats chosenTower) {
        TowerLogic.chosenTower = chosenTower;
    }

    public static TowerStats getChosenTower() {
        return chosenTower;
    }



}
