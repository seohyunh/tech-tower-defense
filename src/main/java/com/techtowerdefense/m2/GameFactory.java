package com.techtowerdefense.m2;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameFactory implements EntityFactory {

    @Spawns("CANNONTOWER")
    public Entity spawnCannonTower(SpawnData data) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new
                FileInputStream("./src/main/resources/assets/textures/cannonTower.png")));
        image.setFitWidth(80);
        image.setFitHeight(80);
        return FXGL.entityBuilder(data)
                .zIndex(9)
                .viewWithBBox(image)
                .build();

    }

    @Spawns("NINJABUZZTOWER")
    public Entity spawnNinjaBuzzTower(SpawnData data) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new
                FileInputStream("./src/main/resources/assets/textures/ninjaBuzzTower.png")));
        image.setFitWidth(80);
        image.setFitHeight(80);

        return FXGL.entityBuilder(data)
                .viewWithBBox(image)
                .build();

    }

    @Spawns("REGBUZZTOWER")
    public Entity spawnRegBuzzTower(SpawnData data) throws FileNotFoundException {
        ImageView image = new ImageView(new Image(new
                FileInputStream("./src/main/resources/assets/textures/regBuzzTower.png")));
        image.setFitWidth(80);
        image.setFitHeight(80);

        return FXGL.entityBuilder(data)
                .viewWithBBox(image)
                .build();

    }

}
