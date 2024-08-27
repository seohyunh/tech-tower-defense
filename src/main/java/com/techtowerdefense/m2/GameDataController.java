package com.techtowerdefense.m2;

public final class GameDataController {


    private int money;

    private int health;

    private int price;

    private final float appWidth =    1920.0f;

    private  final  float appHeight =   1080.0f;


    public float getappwidth() {
        return appWidth;
    }

    public float getappheight() {
        return appHeight;
    }



    public int getMoney() {
        return money;
    }

    public int getHealth() {
        return health;
    }

    public int getPriceFact() {
        return price;
    }

    // other service logics
    private MoneyLogic moneyLogic = new MoneyLogic();

    private HealthLogic healthLogic = new HealthLogic();

//    private PriceLogic priceLogic = new PriceLogic();

    //follow singleton getInstance
    private static GameDataController instance;

    private GameDataController() {
        //change this controller to be a singleton,
        // so the same instance can be called anywhere
    }

    //follow singleton getInstance
    public static GameDataController getInstance() {

        if (instance == null) {
            instance = new GameDataController();
        }

        return instance;

    }


    public void setMoney(String difficulty) {
        //follow OCP
        money = moneyLogic.setAttributeByDifficulty(difficulty);
    }

    public void setMoney(int newMoney) {
        money = newMoney;
    }

    public void setHealth(String difficulty) {
        //follow OCP
        health = healthLogic.setAttributeByDifficulty(difficulty);
    }
    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public int setPriceFact(String difficulty) {
        if (difficulty.equals("Easy")) {
            price = 1;
        } else if (difficulty.equals("Less easy")) {
            price = 2;
        } else if (difficulty.equals("Way less easy")) {
            price = 3;
        }
        return price;
    }




}
