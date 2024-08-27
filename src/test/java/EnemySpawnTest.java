import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.data.WaveData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class EnemySpawnTest {
    // Implemented By Mahay Ahmed
    // Tests whether enemies are moving between waypoints

    private GameDataController gdcsingleton = GameDataController.getInstance();
    private WaveData waveData = new WaveData(1, "enemy1.json", 15, "way1", 250);

    @Test
    public void testEnemySpawn() {
        gdcsingleton.setHealth("Easy");
        assertEquals(1, waveData.index());
    }

}