package com.techtowerdefense.m2;

public class HealthLogic implements ImportantAttribute {

    private int health;

    @Override
    public int setAttributeByDifficulty(String difficulty) {

        if (difficulty.equals("Easy")) {
            health = 160;
        } else if (difficulty.equals("Less easy")) {
            health = 100;
        } else if (difficulty.equals("Way less easy")) {
            health = 60;
        }
        return health;
    }
}
