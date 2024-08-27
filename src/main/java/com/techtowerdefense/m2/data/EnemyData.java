package com.techtowerdefense.m2.data;


public record EnemyData(
        int hp,
        int reward,
        int damage,
        double moveSpeed,
        double interval,
        String imageName
) { }
