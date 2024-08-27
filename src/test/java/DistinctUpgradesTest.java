import com.techtowerdefense.m2.TowerStats;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DistinctUpgradesTest {
    // Implemented By Mahay Ahmed
    // Tests whether damage changes when tower is upgraded

    //Damage of reg tower
    private String damage = TowerStats.CANNONTOWER.getImagePath();
    // Damage of upgraded Tower
    private String upgradeDamage = TowerStats.NINJABUZZTOWER.getImagePath();


    @Test
    public void TowerDistinction() {
        assertTrue((damage != upgradeDamage));
    }
}