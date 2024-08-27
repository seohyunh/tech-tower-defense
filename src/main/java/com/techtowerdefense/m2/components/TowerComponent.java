package com.techtowerdefense.m2.components;

import com.almasb.fxgl.dsl.effects.SlowTimeEffect;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.time.LocalTimer;
import com.techtowerdefense.m2.EntityType;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.data.TowerData;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

import static com.almasb.fxgl.dsl.FXGL.*;


public class TowerComponent extends Component {

    private LocalTimer shootTimer;

    private TowerData data;

    public TowerComponent(TowerData data) {
        this.data = data;
    }

    public int getDamage() {
        return data.attack();
    }

    private static GameDataController gdcsingleton = GameDataController.getInstance();

    private static Rectangle2D firstThird =
            new Rectangle2D(0, 0, gdcsingleton.getappwidth()/3, gdcsingleton.getappheight());
    private static Rectangle2D secondThird =
            new Rectangle2D(gdcsingleton.getappwidth()/3, 0, gdcsingleton.getappwidth()/3, gdcsingleton.getappheight());
    private static Rectangle2D lastThird =
            new Rectangle2D(2*gdcsingleton.getappwidth()/3, 0, gdcsingleton.getappwidth()/3, gdcsingleton.getappheight());

    // TODO: read from data
//    public List<OnHitEffect> onHitEffects() {
//        return List.of(
//                new OnHitEffect(new SlowTimeEffect(0.2, Duration.seconds(3)), 0.75)
//        );
//    }

    @Override
    public void onAdded() {
        shootTimer = newLocalTimer();
        shootTimer.capture();
    }

    @Override
    public void onUpdate(double tpf) {
        if (shootTimer.elapsed(Duration.seconds(1.5))) {
            getGameWorld()
                    .getClosestEntity(entity, e -> e.isType(EntityType.ENEMY))
                    .ifPresent(nearestEnemy -> {

                        entity.rotateToVector(nearestEnemy.getPosition().subtract(entity.getPosition()));
                        entity.rotateBy(90);
                        if(entity.isWithin(firstThird) && nearestEnemy.isWithin(firstThird)){
                            shoot(nearestEnemy);
                            shootTimer.capture();
                            System.out.println("This tower is in the first third");
                        }
                        else if(entity.isWithin(secondThird) && nearestEnemy.isWithin(secondThird)){
                            shoot(nearestEnemy);
                            shootTimer.capture();
                            System.out.println("This tower is in the second third");
                            System.out.println(nearestEnemy.getPosition());
                            System.out.println(nearestEnemy.isWithin(secondThird));
                            System.out.println(nearestEnemy.isWithin(lastThird));
                        }
                        else if(entity.isWithin(lastThird) && nearestEnemy.isWithin(lastThird)){
                            shoot(nearestEnemy);
                            shootTimer.capture();
                            System.out.println("This tower is in the last third");
                            System.out.println(nearestEnemy.isWithin(firstThird));
                            System.out.println(nearestEnemy.isWithin(secondThird));
                            System.out.println(nearestEnemy.isWithin(lastThird));
                        }
                    });
        }
    }

    private void shoot(Entity enemy) {
        Point2D position = getEntity().getPosition();

        Point2D direction = enemy.getPosition().subtract(position);

        String imageName = Objects.requireNonNullElse(data.projectileImageName(), "projectile.png");

        var bullet = spawn("Bullet",
                new SpawnData(position)
                        .put("imageName", imageName)
                        .put("tower", entity)
                        .put("target", enemy)
        );
        bullet.rotateToVector(direction);
    }

    public TowerData getData(){return data;}
}
