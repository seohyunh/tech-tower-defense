package com.techtowerdefense.m2.components;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.techtowerdefense.m2.SimpleGameApp;
import com.techtowerdefense.m2.data.EnemyData;
import com.techtowerdefense.m2.data.Way;
import javafx.geometry.Point2D;

import java.util.List;


public class EnemyComponent extends Component {

    private List<Point2D> waypoints;
    private EnemyData data;
    private Point2D nextWaypoint;

    public EnemyComponent(Way way, EnemyData data) {
        waypoints = way.getWaypoints();
        this.data = data;
    }

    public static boolean enemyAttack(int attack) {
        if (attack != 10) {
            boolean enemyReachedEnd = false;
        }
        boolean enemyReachedEnd = true;
        return enemyReachedEnd;
    }

    public EnemyData getData() {
        return data;
    }

    @Override
    public void onAdded() {
        nextWaypoint = waypoints.remove(0);

        entity.setPosition(nextWaypoint);
    }

    @Override
    public void onUpdate(double tpf) {
        double speed = tpf * 60 * data.moveSpeed();

        Point2D velocity = nextWaypoint.subtract(entity.getPosition())
                .normalize()
                .multiply(speed);

        entity.translate(velocity);

        if (nextWaypoint.distance(entity.getPosition()) < speed) {
            entity.setPosition(nextWaypoint);

            if (!waypoints.isEmpty()) {
                nextWaypoint = waypoints.remove(0);
            } else {
                FXGL.<SimpleGameApp>getAppCast().onEnemyReachedEnd(entity);

                entity.removeFromWorld();
            }
        }
    }
}
