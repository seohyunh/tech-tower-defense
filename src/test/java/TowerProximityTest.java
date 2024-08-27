import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.components.EnemyComponent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.junit.Test;

import static com.almasb.fxgl.dsl.FXGL.spawn;
import static org.junit.Assert.assertTrue;

public class TowerProximityTest {
    //This test was written by Mahay Ahmed to ensure Towers only Attack within a certain proximity
    private static GameDataController gdcsingleton = GameDataController.getInstance();

    private static Rectangle2D firstThird =
            new Rectangle2D(0, 0, gdcsingleton.getappwidth()/3, gdcsingleton.getappheight());
    private static Rectangle2D secondThird =
            new Rectangle2D(gdcsingleton.getappwidth()/3, 0, gdcsingleton.getappwidth()/3, gdcsingleton.getappheight());
    private static Rectangle2D lastThird =
            new Rectangle2D(2*gdcsingleton.getappwidth()/3, 0, gdcsingleton.getappwidth()/3, gdcsingleton.getappheight());
    Point2D point = new Point2D(852,783);

    @Test
    public void towerProximityTest() {

        //Sets the position of the enemy
        Entity enemy = new Entity();
        enemy.setPosition(point);
        boolean canShoot = false;
        if (enemy.isWithin(secondThird)) {
            canShoot = true;
        }

        //Asserts that the enemy can be attacked if it is within tower range
        assertTrue(canShoot);

    }

}
