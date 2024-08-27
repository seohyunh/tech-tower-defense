package com.techtowerdefense.m2;

public enum TowerStats {

    REGBUZZTOWER(2, 1, 20, "./src/main/resources/assets/textures/regBuzzTower.png"),
    NINJABUZZTOWER(3, 2, 30, "./src/main/resources/assets/textures/ninjaBuzzTower.png"),
    CANNONTOWER(4, 3, 40, "./src/main/resources/assets/textures/cannonTower.png");

    private final int range;
    private final int damage;
    private final int price;
    private final String imagePath;

    private GameDataController gdcsingleton = GameDataController.getInstance();

    TowerStats(int range, int damage, int price, String imagePath) {
        this.range = range;
        this.damage = damage;
        this.price = price;
        this.imagePath = imagePath;
    }



    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public int getPrice() {
        return price * gdcsingleton.getPriceFact();
    }

    public String getImagePath() {
        return imagePath;
    }
}
