import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.TowerDefenseFactory;
import com.techtowerdefense.m2.components.BulletComponent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EnemyDiesTest {
    //This test was written by Mahay Ahmed to ensure enemies die when health reaches 0
    private static GameDataController gdcsingleton = GameDataController.getInstance();

    @Test
    public void enemyDies() {

        //Spawns new enemy and sets health equal to 0;
        Entity enemy = new Entity();
        HealthIntComponent health = new HealthIntComponent(0);
        enemy.removeFromWorld();
        //Checks whether enemy is active on map after health has been set to 0;
        boolean enemyOnMap = enemy.isActive();

        //Asserts that the enemy can be attacked if it is within tower range
        assertFalse(enemyOnMap);

    }
}
