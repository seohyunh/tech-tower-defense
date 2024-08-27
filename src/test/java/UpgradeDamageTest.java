import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.TowerStats;
import com.techtowerdefense.m2.data.EnemyData;
import com.techtowerdefense.m2.data.TowerData;
import com.techtowerdefense.m2.data.WaveData;
import javafx.util.Duration;
import org.junit.Test;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.spawnWithScale;
import static org.junit.Assert.*;


public class UpgradeDamageTest {
    // Implemented By Mahay Ahmed
    // Tests whether damage changes when tower is upgraded

    //Damage of reg tower
    private int damage = TowerStats.CANNONTOWER.getDamage();
    // Damage of upgraded Tower
    private int upgradeDamage = TowerStats.NINJABUZZTOWER.getDamage();


    @Test
    public void TowerDamageTest() {
        assertTrue((damage != upgradeDamage));
    }
}


