package com.techtowerdefense.m2;

public class MoneyLogic implements ImportantAttribute {

    private int money;
    @Override
    public int setAttributeByDifficulty(String difficulty) {

        if (difficulty.equals("Easy")) {
            money = 300;
        } else if (difficulty.equals("Less easy")) {
            money = 400;
        } else if (difficulty.equals("Way less easy")) {
            money = 500;
        }
        return money;
    }

    //towers should not be able to be bought with insufficient funds
    //method to check if tower can be bought
    public static boolean canBuy(int money, TowerStats tower) {
        if (tower != null) {
            return tower.getPrice() <= money;
        }
        return false;
    }

}
