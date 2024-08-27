import com.techtowerdefense.m2.GameDataController;
import com.techtowerdefense.m2.TowerManager;
import javafx.geometry.Point2D;
import org.junit.Test;

import static org.junit.Assert.assertFalse;


public class GameOverTest {
    // Implemented By Mahay Ahmed
    // Test whether you can still place towers after health is 0.
    private TowerManager towermanager = TowerManager.INSTANCE;
    private GameDataController gdcsingleton = GameDataController.getInstance();

    @Test
    public void testCantPlace() {
        gdcsingleton.setHealth(0);
        Point2D position =
                new Point2D(gdcsingleton.getappwidth() / 2.0,
                        4.1 * gdcsingleton.getappheight() / 6.0);

        assertFalse(towermanager.placeTower(position));
    }
}