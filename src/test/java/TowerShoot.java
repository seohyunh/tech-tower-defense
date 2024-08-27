import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.TowerManager;
import com.techtowerdefense.m2.TowerStats;
import com.techtowerdefense.m2.components.BulletComponent;
import com.techtowerdefense.m2.components.EnemyComponent;
import com.techtowerdefense.m2.data.WaveData;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.junit.Test;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static org.junit.Assert.assertEquals;

public class TowerShoot {
    // test if towers shoot
    private GameDataController gdcsingleton = GameDataController.getInstance();
    private static TowerStats towerStats;
    private WaveData waveData = new WaveData(1, "enemy1.json", 15, "way1", 250);
    private static int initHealth = 200;

    static int damage = towerStats.CANNONTOWER.getDamage();

    @Test
    public void TowerShootTest() {
        // implemented by SeoHyun Hwang if towers can shoot
        Entity enemy = new Entity();
        damage = 2 * damage;
        int currDamage = initHealth - damage;

        assertEquals(194, currDamage);
    }
}
