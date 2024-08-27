import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.components.EnemyComponent;
import com.techtowerdefense.m2.data.EnemyData;
import com.techtowerdefense.m2.data.WaveData;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

// Tests implemented by SeoHyun Hwang
public class SeoHyunTestCases {
    private int amount = 15;
    private String way = "way1";

    WaveData waveData = new WaveData(1,"enemy1.json", amount, way,250);

    @Test
    public void testWay() {
        GameDataController gdcsingleton = GameDataController.getInstance();
        gdcsingleton.setHealth("Less Easy");
        assertEquals("way1", waveData.way());

        gdcsingleton.setHealth("Easy");
        assertEquals("way1", waveData.way());

        gdcsingleton.setHealth("Way Less Easy");
        assertEquals("way1", waveData.way());
   }

    @Test
    public void testEnemyCount() {
        GameDataController gdcsingleton = GameDataController.getInstance();

        gdcsingleton.setHealth("Way Less Easy");
        assertEquals(15, waveData.amount());
    }
}
