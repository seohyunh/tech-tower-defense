
import com.techtowerdefense.m2.data.WaveData;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FinalBossStrengthTest {

    private WaveData normalEnemy = new WaveData(1, "enemy1.json", 15, "way1", 250);
    private WaveData finalBoss = new WaveData(3, "enemy2.json", 1, "way1", 250);
    private boolean healthGreater =  false;
    private int bossHealth = finalBoss.index();
    private int normalHealth = normalEnemy.index();

    @Test
    public void finalBossStrengthTest() {

        if (bossHealth > normalHealth) {
            healthGreater = true;

        } else {
            healthGreater = false;

        }
        assertTrue(healthGreater);

    }
}
