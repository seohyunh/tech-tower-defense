import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.MoneyLogic;
import com.techtowerdefense.m2.TowerLogic;
import com.techtowerdefense.m2.TowerManager;
import com.techtowerdefense.m2.*;
import com.techtowerdefense.m2.components.EnemyComponent;
import com.techtowerdefense.m2.data.EnemyData;
import com.techtowerdefense.m2.data.WaveData;
import com.techtowerdefense.m2.data.Way;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import org.junit.Test;
import static com.techtowerdefense.m2.TowerStats.*;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.techtowerdefense.m2.EntityType.WAY;
import static com.techtowerdefense.m2.TowerStats.CANNONTOWER;
import static org.junit.Assert.assertEquals;


public class TowerCombatButton {
    private Button startButton;
    private Runnable waveStartAction = EmptyRunnable.INSTANCE;
    private GameDataController gdcsingleton = GameDataController.getInstance();
    private WaveData waveData = new WaveData(1, "enemy1.json", 15, "way1", 250);
    private TowerLogic chosenTower;
    private TowerManager tower = TowerManager.INSTANCE;
    private static TowerStats towerStats;

    @Test
    public void TowerCombatButtonTest() {
        //Implemented by SeoHyun Hwang to check if towers can be placed after combat button is pressed.
        nextWave();

        gdcsingleton.setPriceFact("Easy");
        new MoneyLogic().setAttributeByDifficulty("Easy");
        gdcsingleton.setMoney("Easy");
        javafx.geometry.Point2D position = new Point2D(gdcsingleton.getappwidth()/2.0 ,  5.3 * gdcsingleton.getappheight() /6.0 );
        chosenTower.setChosenTower(CANNONTOWER);
        MoneyLogic.canBuy(400, towerStats);

        assertEquals(300, gdcsingleton.getMoney());
    }

    private void nextWave() {
        spawnWave(waveData);
    }

    private void spawnWave(WaveData wave) {
        Entity enemy = new Entity();
    }
}
