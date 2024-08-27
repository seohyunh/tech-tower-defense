package com.techtowerdefense.m2.data;

import java.util.Collections;
import java.util.List;

public record TowerData(
        String name,
        String imageName,
        String projectileImageName,
        int attack,
        double attackRate,
        int cost,
        boolean isSplashDamage,
        List<String> effects
) {

    @Override
    public List<String> effects() {
        return effects != null ? effects : Collections.emptyList();
    }
}
