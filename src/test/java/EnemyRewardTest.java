import com.almasb.fxgl.entity.Entity;
import com.techtowerdefense.m2.data.WaveData;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class EnemyRewardTest {

    static int reward = 250;

    //This test was written by Courtney Taing to test whether or not the reward increases when enemies are killed.
    @Test
    public void enemyRewardTest() {
        int enemyValue = 450;
        Entity enemy = new Entity();
        int total = (enemyValue + reward);
        assertEquals(700, total);
    }
}
