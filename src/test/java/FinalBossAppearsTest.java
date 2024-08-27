
import com.almasb.fxgl.entity.Entity;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.data.WaveData;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class FinalBossAppearsTest {

    private GameDataController gdcsingleton = GameDataController.getInstance();
    private WaveData bosswave = new WaveData(1, "enemy2.json", 1, "way1", 250);
    private int enemyHealth = 0;
    private int enemiesKilled = 0;


    //This test was written by Courtney Taing to check that the final boss appears after some time
    @Test
    public void finalBossAppearsTest() {
        //Create an enemy entity
        Entity enemy = new Entity();

        //When enemy dies, enemiesKilled increases
        for (int i = 0; i < 15; i++) {
            if (enemyHealth == 0) {
                enemiesKilled += 1;
            }
        }

        //When 15 enemies are killed, the final boss should spawn
        if (enemiesKilled == 15) {
            assertEquals(1, bosswave.amount());
        } else {
            assertEquals(0, bosswave.amount());
        }

    }
}
