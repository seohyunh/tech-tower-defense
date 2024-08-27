package com.techtowerdefense.m2;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;


import com.almasb.fxgl.entity.components.TimeComponent;
import com.techtowerdefense.m2.components.BulletComponent;
import com.techtowerdefense.m2.components.EnemyComponent;
import com.techtowerdefense.m2.components.EnemyHealthViewComponent;
import com.techtowerdefense.m2.components.TowerComponent;
import com.techtowerdefense.m2.data.EnemyData;
import com.techtowerdefense.m2.data.TowerData;
import javafx.beans.binding.Bindings;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import static com.almasb.fxgl.dsl.FXGL.texture;
import static com.techtowerdefense.m2.EntityType.*;
import static com.techtowerdefense.m2.data.Config.*;


public class TowerDefenseFactory implements EntityFactory {
    @Spawns("Enemy")
    public Entity spawnEnemy(SpawnData data) {
        EnemyData enemyData = data.get("enemyData");

        return entityBuilder(data)
                .type(ENEMY)
                .viewWithBBox("enemies/" + enemyData.imageName())
                .collidable()
                .with(new TimeComponent())
                .with(new EffectComponent())
                .with(new HealthIntComponent(enemyData.hp()))
                .with(new EnemyComponent(data.get("way"), enemyData))
                .with(new AutoRotationComponent())
                .with(new EnemyHealthViewComponent())
                .build();
    }
    @Spawns("Tower")
    public Entity spawnTower(SpawnData data) {
        TowerData towerData = data.get("towerData");

        return entityBuilder(data)
                .type(TOWER)
                .viewWithBBox(towerData.imageName())
                .collidable()
                .with(new TowerComponent(towerData))
                .zIndex(Z_INDEX_TOWER)
                .build();
    }

    @Spawns("towerBase")
    public Entity newTowerBase(SpawnData data) {
        var rect = new Rectangle(64, 64, Color.RED);
        rect.setOpacity(0.25);

        var cell = entityBuilder(data)
                .type(TOWER_BASE)
                .viewWithBBox(rect)
                .onClick(e -> {
                    FXGL.<SimpleGameApp>getAppCast().onCellClicked(e);
                })
                .build();

        rect.fillProperty().bind(
                Bindings.when(cell.getViewComponent().getParent().hoverProperty())
                        .then(Color.DARKGREEN)
                        .otherwise(Color.GREEN)
        );

        return cell;
    }

    @Spawns("waypoint")
    public Entity newWaypoint(SpawnData data) {
        return entityBuilder(data)
                .type(WAY)
                .build();
    }

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
    @Spawns("Bullet")
    public Entity spawnBullet(SpawnData data) {
        String imageName = data.get("imageName");

        Node view = texture(imageName);
        view.setRotate(90);

        Entity tower = data.get("tower");
        Entity target = data.get("target");

        return entityBuilder(data)
                .type(BULLET)
                .viewWithBBox(view)
                .collidable()
                .with(new BulletComponent(tower, target))
                .with(new AutoRotationComponent())
                .zIndex(Z_INDEX_BULLET)
                .build();
    }

    // VISUAL EFFECTS

    //    @Spawns("visualEffectSlow")
    //    public Entity newVisualEffectSlow(SpawnData data) {
    //        var icon1 = new PowerDownIcon(Color.YELLOW);
    //        var icon2 = new PowerDownIcon(Color.ORANGE);
    //
    //        var icon3 = new PowerDownIcon(Color.YELLOW);
    //        var icon4 = new PowerDownIcon(Color.ORANGE);
    //
    //        var box = new VBox(-5, icon3, icon4);
    //        box.setTranslateX(64.0);
    //        box.setTranslateY(-25.0);
    //
    //        return entityBuilder(data)
    //                .viewWithBBox(new VBox(-5, icon1, icon2))
    //                .viewWithBBox(box)
    //                .scale(0.3, 0.3)
    //                .build();
    //    }
}
